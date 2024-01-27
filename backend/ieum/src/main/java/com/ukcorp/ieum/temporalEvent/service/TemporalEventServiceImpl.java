package com.ukcorp.ieum.temporalEvent.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
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

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TemporalEventServiceImpl implements TemporalEventService {

  private final TemporalEventMapper temporalEventMapper;
  private final TemporalEventRepository temporalEventRepository;
  private final CareRepository careRepository;
  /**
   * 사용자 id에 따른 일정 목록 조회
   * @param careNo
   * @return List<TemporalEventDto>
   * @throws Exception
   */
  @Override
  public List<TemporalEventResponseDto> getList(Long careNo) throws Exception {
    try{
      List<TemporalEvent> list = temporalEventRepository.findByCareInfoCareNo(careNo);
      if(list.isEmpty()){
        log.debug("등록된 일정이 없습니다");
        throw new Exception("등록된 일정이 없습니다.");
      }
      return temporalEventMapper.TemporalEventEntityToResponseDto(list);
    }catch (RuntimeException e) {
      log.debug("조회하는데 오류가 있습니다");
      throw new Exception("조회 오류!");
    }
  }

  /**
   * 일정 id를 통한 일정 상세 조회
   * @param eventNo
   * @return TemporalEventDto
   * @throws Exception
   */
  @Override
  public TemporalEventResponseDto getDetail(Long eventNo) throws Exception {
    try{
      TemporalEvent event = temporalEventRepository.findById(eventNo).orElse(null);;
      if(event != null){
        return temporalEventMapper.TemporalEventEntityToResponseDto(event);
      }else{
        log.debug("존재하지 않는 일정입니다.");
        throw new Exception("존재하지 않는 일정입니다.");
      }
    }catch (RuntimeException e) {
      log.debug("조회하는데 오류가 있습니다");
      throw new Exception("조회 오류!");
    }

  }

  /**
   * eventNo에 해당하는 일정 삭제
   * @param eventNo
   */
  @Override
  @Transactional
  public void deleteEvent(Long eventNo) throws Exception {
    try{
      temporalEventRepository.deleteById(eventNo);
    }catch (EmptyResultDataAccessException e) {
      log.debug("삭제 오류");
      throw new Exception("삭제 오류!");
    }
  }

  /**
   * 일정 등록
   * @param event
   */
  @Override
  @Transactional
  public void registEvent(TemporalEventInsertRequestDto event) throws Exception {
    try{
      CareInfo care = careRepository.findById(event.getCareNo()).orElse(null);
      if(care == null){
        throw new Exception("보호자 정보 조회 오류");
      }

      TemporalEvent entity = temporalEventMapper
              .temporalEventInsertRequestDtoAndCareInfoToTemporalEvent(event,care);
      temporalEventRepository.save(entity);

    } catch (DataIntegrityViolationException e) {
      log.debug("등록 오류");
      throw new Exception("등록 오류!");
    }

  }

  /**
   * 일정 수정
   * @param event
   * @throws Exception
   */

  @Override
  @Transactional
  public void modifyEvent(TemporalEventUpdateRequestDto event) throws Exception {
    try{
      CareInfo care = careRepository.findById(event.getCareNo()).orElse(null);
      if(care == null){
        throw new Exception("보호자 정보 조회 오류");
      }

      TemporalEvent check = temporalEventRepository.findById(event.getEventNo()).orElse(null);
      if(check == null){
        throw new Exception("일정 조회 오류");
      }

      TemporalEvent entity = temporalEventMapper
              .temporalEventUpdateRequestDtoAndCareInfoToTemporalEvent(event,care);
      temporalEventRepository.save(entity);

    } catch (DataIntegrityViolationException e) {
      log.debug("수정하는데 오류가 있습니다");
      throw new Exception("수정 오류!");
    }
  }
}
