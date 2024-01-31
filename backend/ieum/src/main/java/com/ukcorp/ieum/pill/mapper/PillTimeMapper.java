package com.ukcorp.ieum.pill.mapper;


import com.ukcorp.ieum.pill.dto.request.PillTimeInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillTimeUpdateRequestDto;
import com.ukcorp.ieum.pill.dto.response.PillTimeGetResponseDto;
import com.ukcorp.ieum.pill.entity.PillInfo;
import com.ukcorp.ieum.pill.entity.PillTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PillTimeMapper {
  @Mapping(source = "pill.pillInfo.pillInfoNo", target = "pillInfoNo")
  PillTimeGetResponseDto pillInfoToResponseDto(PillTime pill);

  @Mapping(target = "pillTimeNo", ignore = true)
  @Mapping(source = "entity", target = "pillInfo")
  PillTime pillTimeInsertRequestDtoToPillTime(PillTimeInsertRequestDto pillTime, PillInfo entity);

  @Mapping(target = "pillTime.pillInfoNo", ignore = true)
  @Mapping(source = "entity", target = "pillInfo")
  PillTime pillTimeUpdateRequestDtoToPillTime(PillTimeUpdateRequestDto pillTime, PillInfo entity);

  List<PillTimeGetResponseDto> pillTimesToResponseDto(List<PillTime> pillTimes);



}
