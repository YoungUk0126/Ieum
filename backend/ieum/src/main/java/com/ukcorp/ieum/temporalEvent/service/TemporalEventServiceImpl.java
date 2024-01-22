package com.ukcorp.ieum.schedule.service;

import com.ukcorp.ieum.schedule.dto.TemporalEventDto;
import com.ukcorp.ieum.schedule.entity.TemporalEvent;
import com.ukcorp.ieum.schedule.mapper.TemporalEventMapper;
import com.ukcorp.ieum.schedule.repository.TemporalEventRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemporalEventServiceImpl implements TemporalEventService {

  private static TemporalEventMapper temporalEventMapper;
  private static TemporalEventRepository temporalEventRepository;

  @Override
  public List<TemporalEventDto> getList(Long careNo) {
    List<TemporalEvent> list = temporalEventRepository.findByCareInfoCareNo(careNo);
    List<TemporalEventDto> result = new ArrayList<>();
    for(TemporalEvent i : list){
      result.add(temporalEventMapper.convertToDto(i));
    }
    return result;
  }
}
