package com.ukcorp.ieum.temporalEvent.service;

import com.ukcorp.ieum.temporalEvent.dto.TemporalEventDto;

import java.util.List;

public interface TemporalEventService {
  List<TemporalEventDto> getList(Long careNo) throws Exception;
  TemporalEventDto getDetail(Long eventNo) throws Exception;
  void deleteEvent(Long eventNo);
  void registEvent(TemporalEventDto event);
}
