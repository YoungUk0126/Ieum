package com.ukcorp.ieum.temporalEvent.service;

import com.ukcorp.ieum.temporalEvent.dto.TemporalEventDto;
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

  /**
   * 사용자 id에 따른 일정 목록 조회
   * @param careNo
   * @return List<TemporalEventDto>
   * @throws Exception
   */
  @Override
  public List<TemporalEventDto> getList(Long careNo) throws Exception {
    List<TemporalEvent> list = temporalEventRepository.findByCareInfoCareNo(careNo);
    if(list == null || list.isEmpty()){
      log.debug("등록된 일정이 없습니다");
      throw new Exception("등록된 일정이 없습니다.");
    }
    return temporalEventMapper.TemporalEventEntityToDto(list);
  }

  /**
   * 일정 id를 통한 일정 상세 조회
   * @param eventNo
   * @return TemporalEventDto
   * @throws Exception
   */
  @Override
  public TemporalEventDto getDetail(Long eventNo) throws Exception {
    Optional<TemporalEvent> event = temporalEventRepository.findById(eventNo);
    if(event.isPresent()){
      return temporalEventMapper.TemporalEventEntityToDto(event.get());
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
  public void registEvent(TemporalEventDto event){
    temporalEventRepository.save(temporalEventMapper.TemporalEventDtoToEntity(event));
  }

  @Override
  public void modifyEvent(TemporalEventDto event) throws Exception {
    if(temporalEventRepository.findById(event.getEventNo()).isPresent()){
      temporalEventRepository.save(temporalEventMapper.TemporalEventDtoToEntity(event));
    }else{
      log.debug("존재하지 않는 일정입니다.");
      throw new Exception("존재하지 않는 일정입니다.");
    }
  }


}
