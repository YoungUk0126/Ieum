package com.ukcorp.ieum.pill.mapper;


import com.ukcorp.ieum.pill.dto.request.PillTimeRequestDto;
import com.ukcorp.ieum.pill.dto.response.PillTimeGetResponseDto;
import com.ukcorp.ieum.pill.entity.PillInfo;
import com.ukcorp.ieum.pill.entity.PillTime;
import com.ukcorp.ieum.temporalEvent.dto.response.TemporalEventResponseDto;
import com.ukcorp.ieum.temporalEvent.entity.TemporalEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PillTimeMapper {
  @Mapping(source = "pill.pillInfo.pillInfoNo", target = "pillInfoNo")
  PillTimeGetResponseDto pillInfoToResponseDto(PillTime pill);

  @Mapping(source = "pillInfo", target = "pillInfo")
  PillTime pillTimeRequestDtoToPillTime(PillTimeRequestDto pillTime, PillInfo pillInfo);

  List<PillTimeGetResponseDto> pillTimesToResponseDto(List<PillTime> pillTimes);



}
