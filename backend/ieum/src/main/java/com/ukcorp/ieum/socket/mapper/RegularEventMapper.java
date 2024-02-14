package com.ukcorp.ieum.socket.mapper;

import com.ukcorp.ieum.event.entity.RegularEvent;
import com.ukcorp.ieum.socket.dto.RegularEventDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegularEventMapper {

    @Named("eToD")
    RegularEventDto regularEventToDto(RegularEvent event);

    @IterableMapping(qualifiedByName = "eToD")
    List<RegularEventDto> regularEventsToListDto(List<RegularEvent> events);
}
