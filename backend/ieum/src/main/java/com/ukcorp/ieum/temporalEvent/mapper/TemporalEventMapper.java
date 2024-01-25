package com.ukcorp.ieum.temporalEvent.mapper;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.temporalEvent.dto.request.TemporalEventInsertRequestDto;
import com.ukcorp.ieum.temporalEvent.dto.request.TemporalEventUpdateRequestDto;
import com.ukcorp.ieum.temporalEvent.dto.response.TemporalEventResponseDto;
import com.ukcorp.ieum.temporalEvent.entity.TemporalEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TemporalEventMapper {

  @Mapping(source="event.careInfo.careNo", target="careNo")
  TemporalEventResponseDto TemporalEventEntityToResponseDto(TemporalEvent event);

  List<TemporalEventResponseDto> TemporalEventEntityToResponseDto(List<TemporalEvent> temporalEvent);

  @Mapping(target = "event.careNo", ignore = true)
  @Mapping(source = "care", target = "careInfo")
  TemporalEvent temporalEventUpdateRequestDtoAndCareInfoToTemporalEvent(TemporalEventUpdateRequestDto event, CareInfo care);

  @Mapping(target = "eventNo", ignore = true)
  @Mapping(target = "event.careNo", ignore = true)
  @Mapping(source = "care", target = "careInfo")
  TemporalEvent temporalEventInsertRequestDtoAndCareInfoToTemporalEvent(TemporalEventInsertRequestDto event, CareInfo care);


}
