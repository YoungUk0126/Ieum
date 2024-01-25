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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
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
    List<TemporalEvent> list = temporalEventRepository.findByCareInfoCareNo(careNo);
    if(list == null || list.isEmpty()){
      log.debug("등록된 일정이 없습니다");
      throw new Exception("등록된 일정이 없습니다.");
    }
    return temporalEventMapper.TemporalEventEntityToResponseDto(list);
  }

  /**
   * 일정 id를 통한 일정 상세 조회
   * @param eventNo
   * @return TemporalEventDto
   * @throws Exception
   */
  @Override
  public TemporalEventResponseDto getDetail(Long eventNo) throws Exception {
    Optional<TemporalEvent> event = temporalEventRepository.findById(eventNo);
    if(event.isPresent()){
      return temporalEventMapper.TemporalEventEntityToResponseDto(event.get());
    }else{
      log.debug("존재하지 않는 일정입니다.");
      throw new Exception("존재하지 않는 일정입니다.");
    }
  }

  /**
   * eventNo에 해당하는 일정 삭제
   * @param eventNo
   */
  @Override
  public void deleteEvent(Long eventNo){
    temporalEventRepository.deleteById(eventNo);
  }

  /**
   * 일정 등록
   * @param event
   */
  @Override
  public void registEvent(TemporalEventInsertRequestDto event) throws Exception {
    try{
      Optional<CareInfo> careGet = careRepository.findById(event.getCareNo());
      if(careGet.isEmpty()){
        throw new Exception("보호자 정보 조회 오류");
      }

      CareInfo care = careGet.get();
      TemporalEvent entity = temporalEventMapper
              .temporalEventInsertRequestDtoAndCareInfoToTemporalEvent(event,care);
      temporalEventRepository.save(entity);

    } catch (RuntimeException e) {
      throw new Exception("입력 오류!");
    }

  }

  @Override
  public void modifyEvent(TemporalEventUpdateRequestDto event) throws Exception {
    try{
      Optional<CareInfo> careGet = careRepository.findById(event.getCareNo());
      if(careGet.isEmpty()){
        throw new Exception("보호자 정보 조회 오류");
      }
      CareInfo care = careGet.get();
      TemporalEvent entity = temporalEventMapper
              .temporalEventUpdateRequestDtoAndCareInfoToTemporalEvent(event,care);
      temporalEventRepository.save(entity);

    } catch (RuntimeException e) {
      throw new Exception("입력 오류!");
    }

  }


}
