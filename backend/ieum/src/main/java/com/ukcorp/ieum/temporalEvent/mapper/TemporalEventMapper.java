package com.ukcorp.ieum.temporalEvent.mapper;

import com.ukcorp.ieum.temporalEvent.dto.TemporalEventDto;
import com.ukcorp.ieum.temporalEvent.entity.TemporalEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemporalEventMapper {

  TemporalEventDto TemporalEventEntityToDto(TemporalEvent temporalEventDto);

  TemporalEvent TemporalEventDtoToEntity(TemporalEventDto temporalEvent);


}
