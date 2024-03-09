package com.ukcorp.ieum.socket.mapper;

import com.ukcorp.ieum.event.entity.RegularEvent;
import com.ukcorp.ieum.socket.dto.AnyEventDto;
import com.ukcorp.ieum.socket.dto.RegularEventDto;
import com.ukcorp.ieum.temporalEvent.entity.TemporalEvent;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnyEventSocketMapper {

  @Named("eToD")
  AnyEventDto temporalEventToDto(TemporalEvent event);

  @IterableMapping(qualifiedByName = "eToD")
  List<AnyEventDto> temporalEventsToListDto(List<TemporalEvent> events);
}
