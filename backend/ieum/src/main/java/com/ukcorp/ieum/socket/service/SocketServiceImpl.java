package com.ukcorp.ieum.socket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.event.entity.RegularEvent;
import com.ukcorp.ieum.event.repository.EventRepository;
import com.ukcorp.ieum.jwt.JwtUtil;
import com.ukcorp.ieum.meal.entity.Meal;
import com.ukcorp.ieum.meal.repository.MealRepository;
import com.ukcorp.ieum.message.repository.MessageRepository;
import com.ukcorp.ieum.pill.entity.PillInfo;
import com.ukcorp.ieum.pill.repository.PillInfoRepository;
import com.ukcorp.ieum.sleep.entity.SleepInfo;
import com.ukcorp.ieum.sleep.repository.SleepRepository;
import com.ukcorp.ieum.socket.dto.AnyEventDto;
import com.ukcorp.ieum.socket.dto.PillInfoDto;
import com.ukcorp.ieum.socket.dto.RegularEventDto;
import com.ukcorp.ieum.socket.dto.response.*;
import com.ukcorp.ieum.socket.entity.Content;
import com.ukcorp.ieum.socket.mapper.*;
import com.ukcorp.ieum.temporalEvent.entity.TemporalEvent;
import com.ukcorp.ieum.temporalEvent.repository.TemporalEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SocketServiceImpl implements SocketService {

  //    private final ChannelTopic topic;
  private final RedisPublisher redisPublisher;

  private final CareRepository careRepository;
  private final PillInfoRepository pillInfoRepository;
  private final EventRepository eventRepository;
  private final SleepRepository sleepRepository;
  private final MealRepository mealRepository;
  private final TemporalEventRepository temporalEventRepository;

  private final PillSocketMapper pillSocketMapper;
  private final MealSocketMapper mealSocketMapper;
  private final RegularEventMapper regularEventMapper;
  private final SleepSocketMapper sleepSocketMapper;
  private final AnyEventSocketMapper anyEventSocketMapper;

  @Override
  public void sendPillDataToIot(Long careNo) {
    CareInfo careInfo = careRepository.findCareInfoByCareNo(careNo)
        .orElseThrow(() -> new NoSuchElementException("NOT FOUND CARE INFO"));
    List<PillInfo> byCareInfoCareNo = pillInfoRepository.findByCareInfo_CareNo(careNo);

    String serial = careInfo.getCareSerial();
    ChannelTopic topic = new ChannelTopic(serial);

    List<PillInfoDto> pillInfoDtos = pillSocketMapper.infoesToDtoes(byCareInfoCareNo);
    PillResponseDto pillResponseDto = PillResponseDto.builder()
        .contents(pillInfoDtos)
        .build();

    Content content = new Content();
    try {
      content.pillToContent(pillResponseDto);
      redisPublisher.publishPojo(topic, content);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void sendEventDataToIot(Long careNo) {
    CareInfo careInfo = careRepository.findCareInfoByCareNo(careNo)
        .orElseThrow(() -> new NoSuchElementException("NOT FOUND CARE INFO"));
    List<RegularEvent> allByCareInfoCareNo = eventRepository.findAllByCareInfo_CareNo(careNo);

    String serial = careInfo.getCareSerial();
    ChannelTopic topic = new ChannelTopic(serial);

    List<RegularEventDto> lists = regularEventMapper.regularEventsToListDto(allByCareInfoCareNo);

    EventResponseDto eventResponseDto = EventResponseDto.builder()
        .list(lists)
        .build();

    Content content = new Content();
    try {
      content.eventToContent(eventResponseDto);
      redisPublisher.publishPojo(topic, content);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void sendSleepDataToIot(Long careNo) {
    CareInfo careInfo = careRepository.findCareInfoByCareNo(careNo)
        .orElseThrow(() -> new NoSuchElementException("NOT FOUND CARE INFO"));
    SleepInfo sleepInfo = sleepRepository.findByCareInfo_CareNo(careNo)
        .orElseThrow(() -> new NoSuchElementException("NOT FOUND SLEEP INFO"));

    ChannelTopic topic = new ChannelTopic(careInfo.getCareSerial());

    Content content = new Content();
    SleepResponseDto sleepResponseDto = sleepSocketMapper.InfoToDto(sleepInfo);

    try {
      content.sleepToContent(sleepResponseDto);
      redisPublisher.publishPojo(topic, content);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void sendMealDataToIot(Long careNo) {
    CareInfo careInfo = careRepository.findCareInfoByCareNo(careNo)
        .orElseThrow(() -> new NoSuchElementException("NOT FOUND CARE INFO"));
    Meal meal = mealRepository.findByCareInfo_CareNo(careNo)
        .orElseThrow(() -> new NoSuchElementException("NOT FOUND MEAL"));

    ChannelTopic topic = new ChannelTopic(careInfo.getCareSerial());

    Content content = new Content();
    MealResponseDto mealResponseDto = mealSocketMapper.mealToMealResponseDto(meal);
    try {
      content.mealToContent(mealResponseDto);
      redisPublisher.publishPojo(topic, content);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void sendMessageToIot(Long careNo) {

  }

  @Override
  public void sendCallAlertToIot() {

    Long careNo = JwtUtil.getCareNo().orElseThrow(
            () -> new NoSuchElementException("토큰에 CareNo에 없어요"));

    CareInfo careInfo = careRepository.findCareInfoByCareNo(careNo)
        .orElseThrow(() -> new NoSuchElementException("NOT FOUND CARE INFO"));

    ChannelTopic topic = new ChannelTopic(careInfo.getCareSerial());

    Content content = new Content();
    content.callToContent();
    redisPublisher.publishPojo(topic, content);
  }

  @Override
  public void sendCallStopAlertToIot() {
    Long careNo = JwtUtil.getCareNo().orElseThrow(
            () -> new NoSuchElementException("토큰에 CareNo에 없어요"));

    CareInfo careInfo = careRepository.findCareInfoByCareNo(careNo)
            .orElseThrow(() -> new NoSuchElementException("NOT FOUND CARE INFO"));

    ChannelTopic topic = new ChannelTopic(careInfo.getCareSerial());

    Content content = new Content();
    content.callStopToContent();
    redisPublisher.publishPojo(topic, content);
  }

  @Override
  public void sendAnyEventToIot(Long careNo) {
    CareInfo careInfo = careRepository.findCareInfoByCareNo(careNo)
            .orElseThrow(() -> new NoSuchElementException("NOT FOUND CARE INFO"));
    List<TemporalEvent> events = temporalEventRepository.findByCareInfoCareNoOrderByEventDate(careInfo.getCareNo());
    List<AnyEventDto> anyEvents = anyEventSocketMapper.temporalEventsToListDto(events);

    AnyEventResponseDto eventResponseDto = AnyEventResponseDto.builder()
            .list(anyEvents)
            .build();

    String serial = careInfo.getCareSerial();
    ChannelTopic topic = new ChannelTopic(serial);
    Content content = new Content();
    try {
      content.anyEventToContent(eventResponseDto);
      redisPublisher.publishPojo(topic, content);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
