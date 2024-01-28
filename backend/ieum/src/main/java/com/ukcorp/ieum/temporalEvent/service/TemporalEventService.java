package com.ukcorp.ieum.temporalEvent.service;

import com.ukcorp.ieum.temporalEvent.dto.request.TemporalEventInsertRequestDto;
import com.ukcorp.ieum.temporalEvent.dto.request.TemporalEventUpdateRequestDto;
import com.ukcorp.ieum.temporalEvent.dto.response.TemporalEventResponseDto;

import java.util.List;

public interface TemporalEventService {
  List<TemporalEventResponseDto> getList(Long careNo) throws Exception;
  TemporalEventResponseDto getDetail(Long eventNo) throws Exception;
  void deleteEvent(Long eventNo);
  void registEvent(TemporalEventInsertRequestDto event) throws Exception;

  void modifyEvent(TemporalEventUpdateRequestDto event) throws Exception;
}
