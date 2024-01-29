package com.ukcorp.ieum.temporalEvent.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.jwt.JwtUtil;
import com.ukcorp.ieum.temporalEvent.dto.request.TemporalEventInsertRequestDto;
import com.ukcorp.ieum.temporalEvent.dto.request.TemporalEventUpdateRequestDto;
import com.ukcorp.ieum.temporalEvent.dto.response.TemporalEventResponseDto;
import com.ukcorp.ieum.temporalEvent.entity.TemporalEvent;
import com.ukcorp.ieum.temporalEvent.mapper.TemporalEventMapper;
import com.ukcorp.ieum.temporalEvent.repository.TemporalEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TemporalEventServiceImpl implements TemporalEventService {

  private final TemporalEventMapper temporalEventMapper;
  private final TemporalEventRepository temporalEventRepository;
  private final CareRepository careRepository;

  @Override
  public List<TemporalEventResponseDto> getList() throws Exception {
    try {
      Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo에 없어요"));

      List<TemporalEvent> list = temporalEventRepository.findByCareInfoCareNo(careNo);

      return temporalEventMapper.TemporalEventEntityToResponseDto(list);
    } catch (RuntimeException e) {
      log.debug("조회하는데 오류가 있습니다");
      throw new Exception("조회 오류!");
    }
  }


  @Override
  public TemporalEventResponseDto getDetail(Long eventNo) throws Exception {
    try {
      TemporalEvent event = temporalEventRepository.findById(eventNo).orElseThrow(() -> new NoSuchElementException("존재하지 않는 일정입니다."));

      return temporalEventMapper.TemporalEventEntityToResponseDto(event);
    } catch (RuntimeException e) {
      log.debug("조회하는데 오류가 있습니다");
      throw new Exception("조회 오류!");
    }
  }

  @Override
  @Transactional
  public void deleteEvent(Long eventNo) throws Exception {
    try {
      temporalEventRepository.deleteById(eventNo);
    } catch (EmptyResultDataAccessException e) {
      log.debug("삭제 오류");
      throw new Exception("삭제 오류!");
    }
  }

  @Override
  @Transactional
  public void registEvent(TemporalEventInsertRequestDto event) throws Exception {
    try {
      Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo에 없어요"));

      CareInfo care = careRepository.findById(careNo).orElseThrow(() -> new NoSuchElementException("보호자 정보 조회 오류."));

      TemporalEvent entity = temporalEventMapper
              .temporalEventInsertRequestDtoAndCareInfoToTemporalEvent(event, care);

      temporalEventRepository.save(entity);
    } catch (DataIntegrityViolationException e) {
      log.debug("등록 오류");
      throw new Exception("등록 오류!");
    }
  }

  @Override
  @Transactional
  public void modifyEvent(TemporalEventUpdateRequestDto event) throws Exception {
    try {
      Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo에 없어요"));

      CareInfo care = careRepository.findById(careNo).orElseThrow(() -> new NoSuchElementException("보호자 정보 조회 오류."));

      if (temporalEventRepository.existsById(event.getEventNo())) {
        throw new NoSuchElementException("존재하지 않는 일정입니다.");
      }

      TemporalEvent entity = temporalEventMapper
              .temporalEventUpdateRequestDtoAndCareInfoToTemporalEvent(event, care);
      temporalEventRepository.save(entity);

    } catch (DataIntegrityViolationException e) {
      log.debug("수정하는데 오류가 있습니다");
      throw new Exception("수정 오류!");
    }
  }
}