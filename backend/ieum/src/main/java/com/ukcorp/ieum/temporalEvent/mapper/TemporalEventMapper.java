package com.ukcorp.ieum.temporalEvent.mapper;

import com.ukcorp.ieum.temporalEvent.dto.TemporalEventDto;
import com.ukcorp.ieum.temporalEvent.entity.TemporalEvent;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TemporalEventMapper {

  TemporalEventDto TemporalEventEntityToDto(TemporalEvent temporalEventDto);

  TemporalEvent TemporalEventDtoToEntity(TemporalEventDto temporalEvent);

  List<TemporalEventDto> TemporalEventEntityToDto(List<TemporalEvent> temporalEvent);

}
