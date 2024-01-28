package com.ukcorp.ieum.event.service;


import com.ukcorp.ieum.event.dto.request.EventInsertRequestDto;
import com.ukcorp.ieum.event.dto.request.EventUpdateRequestDto;
import com.ukcorp.ieum.event.dto.response.EventGetResponseDto;

import java.util.List;

public interface EventService {
  void insertEvent(EventInsertRequestDto event) throws Exception;

  EventGetResponseDto getEvent(Long eventNo) throws Exception;

  List<EventGetResponseDto> getAllEvent(Long careNo) throws Exception;

  void updateEvent(EventUpdateRequestDto event) throws Exception;

  void deleteEvent(Long eventNo);
}
