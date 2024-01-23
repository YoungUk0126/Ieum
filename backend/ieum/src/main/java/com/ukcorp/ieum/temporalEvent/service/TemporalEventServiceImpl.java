package com.ukcorp.ieum.temporalEvent.service;

import com.ukcorp.ieum.temporalEvent.dto.TemporalEventDto;
import com.ukcorp.ieum.temporalEvent.entity.TemporalEvent;
import com.ukcorp.ieum.temporalEvent.mapper.TemporalEventMapper;
import com.ukcorp.ieum.temporalEvent.repository.TemporalEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
      throw new Exception("존재하지 않는 번호입니다.");
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
}
