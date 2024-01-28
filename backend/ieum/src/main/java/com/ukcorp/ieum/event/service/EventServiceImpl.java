package com.ukcorp.ieum.event.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.event.dto.request.EventInsertRequestDto;
import com.ukcorp.ieum.event.dto.request.EventUpdateRequestDto;
import com.ukcorp.ieum.event.dto.response.EventGetResponseDto;
import com.ukcorp.ieum.event.entity.RegularEvent;
import com.ukcorp.ieum.event.mapper.EventMapper;
import com.ukcorp.ieum.event.repository.EventRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;
  private final CareRepository careRepository;
  private final EventMapper eventMapper;


  @Transactional
  @Override
  public void insertEvent(EventInsertRequestDto event) throws Exception {
    try {
      CareInfo care = careRepository.findById(event.getCareNo()).orElse(null);
      if (care == null) {
        throw new Exception("보호자 정보 조회 오류");
      }
      RegularEvent entity = eventMapper
              .eventInsertRequestDtoAndCareToRegularEvent(event, care);
      eventRepository.save(entity);

    } catch (DataIntegrityViolationException e) {
      log.debug("입력 오류!");
      throw new Exception("입력 오류!");
    }
  }

  @Override
  public EventGetResponseDto getEvent(Long eventNo) throws Exception {
    try{
      RegularEvent event = eventRepository.findById(eventNo).orElse(null);
      if(event != null) {
        return eventMapper.RegularEventToEventGetResponseDto(event);
      } else {
        log.debug("등록된 기념일이 없습니다");
        throw new Exception("등록된 기념일이 없습니다");
      }
    } catch (RuntimeException e) {
      log.debug("조회 오류!");
      throw new Exception("조회 오류!");
    }
  }

  @Override
  public List<EventGetResponseDto> getAllEvent(Long careNo) throws Exception {
    try{
      List<RegularEvent> list = eventRepository.findAllByCareInfo_CareNo(careNo);
      if(list.isEmpty()) {
        log.debug("등록된 기념일이 없습니다");
        throw new Exception("등록된 기념일이 없습니다");
      }
      return eventMapper.RegularEventEntityToResponseDto(list);
    } catch (RuntimeException e) {
      log.debug("조회 오류!");
      throw new Exception("조회 오류!");
    }

  }

  @Transactional
  @Override
  public void updateEvent(EventUpdateRequestDto event) throws Exception {
    try {
      CareInfo care = careRepository.findById(event.getCareNo()).orElse(null);
      if (care == null) {
        throw new Exception("피보호자 정보 조회 오류");
      }
      RegularEvent check = eventRepository.findById(event.getEventNo()).orElse(null);
      if(check == null) {
        throw new Exception("수정하려는 기념일 조회 오류");
      }
      RegularEvent entity = eventMapper
              .EventUpdateRequestDtoAndCareInfoToRegualrEvent(event, care);
      eventRepository.save(entity);

    } catch (DataIntegrityViolationException e) {
      log.debug("수정 오류!");
      throw new Exception("수정 오류!");
    }
  }

  @Transactional
  @Override
  public void deleteEvent(Long eventNo) throws Exception {
    try {
      eventRepository.deleteById(eventNo);
    } catch (EmptyResultDataAccessException e) {
      log.debug("삭제 오류!");
      throw new Exception("삭제 오류!");
    }

  }
}
