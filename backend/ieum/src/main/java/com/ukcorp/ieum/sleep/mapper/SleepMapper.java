package com.ukcorp.ieum.sleep.mapper;


import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.sleep.dto.request.SleepInsertRequestDto;
import com.ukcorp.ieum.sleep.dto.request.SleepUpdateRequestDto;
import com.ukcorp.ieum.sleep.dto.response.SleepResponseDto;
import com.ukcorp.ieum.sleep.entity.SleepInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SleepMapper {

  @Mapping(source = "sleep.careInfo.careNo", target = "careNo")
  SleepResponseDto SleepInfoToResponseDto(SleepInfo sleep);


  // update
  @Mapping(target = "sleep.careNo", ignore = true)
  @Mapping(source = "care", target = "careInfo")
  SleepInfo sleepUpdateRequestDtoAndCareInfoToSleepInfo(SleepUpdateRequestDto sleep, CareInfo care);

  //  insert
  @Mapping(target = "sleepInfoNo", ignore = true)
  @Mapping(target = "event.careNo", ignore = true)
  @Mapping(source = "care", target = "careInfo")
  SleepInfo sleepInsertRequestDtoAndCareInfoToSleepInfo(SleepInsertRequestDto sleep, CareInfo care);

}
