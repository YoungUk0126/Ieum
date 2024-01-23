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

  @Override
  public List<TemporalEventDto> getList(Long careNo) throws Exception {
    List<TemporalEvent> list = temporalEventRepository.findByCareInfoCareNo(careNo);
    if(list == null || list.isEmpty()){
      throw new Exception("등록된 일정이 없습니다.");
    }
    return temporalEventMapper.TemporalEventEntityToDto(list);
  }

  @Override
  public TemporalEventDto getDetail(Long eventNo) throws Exception {
    Optional<TemporalEvent> event = temporalEventRepository.findById(eventNo);
    if(event.isPresent()){
      return temporalEventMapper.TemporalEventEntityToDto(event.get());
    }else{
      throw new Exception("존재하지 않는 번호입니다.");
    }
  }

  @Override
  public void deleteEvent(Long eventNo){
    temporalEventRepository.deleteById(eventNo);
  }
}
