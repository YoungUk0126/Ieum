package com.ukcorp.ieum.temporalEvent.service;

import com.ukcorp.ieum.temporalEvent.dto.TemporalEventDto;
import com.ukcorp.ieum.temporalEvent.entity.TemporalEvent;
import com.ukcorp.ieum.temporalEvent.mapper.TemporalEventMapper;
import com.ukcorp.ieum.temporalEvent.repository.TemporalEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemporalEventServiceImpl implements TemporalEventService {

  private final TemporalEventMapper temporalEventMapper;
  private final TemporalEventRepository temporalEventRepository;

  @Override
  public List<TemporalEventDto> getList(Long careNo) {
    List<TemporalEvent> list = temporalEventRepository.findByCareInfoCareNo(careNo);

    return temporalEventMapper.TemporalEventEntityToDto(list);
  }
}
