package com.ukcorp.ieum.event.mapper;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.event.dto.request.EventInsertRequestDto;
import com.ukcorp.ieum.event.dto.response.EventGetResponseDto;
import com.ukcorp.ieum.event.entity.RegularEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "eventNo", ignore = true)
    @Mapping(target = "eventInsertRequestDto.careNo", ignore = true)
    @Mapping( source = "care", target = "careInfo")
    RegularEvent eventInsertRequestDtoAndCareToRegularEvent(EventInsertRequestDto eventInsertRequestDto, CareInfo care);

    @Mapping(source = "event.careInfo.careNo", target= "careNo")
    EventGetResponseDto RegularEventToEventGetResponseDto(RegularEvent event);
}
