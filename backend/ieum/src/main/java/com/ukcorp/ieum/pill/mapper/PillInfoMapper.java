package com.ukcorp.ieum.pill.mapper;


import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.pill.dto.request.PillInfoInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillInfoUpdateRequestDto;
import com.ukcorp.ieum.pill.dto.response.PillInfoGetResponseDto;
import com.ukcorp.ieum.pill.dto.response.PillInfoJoinResponseDto;
import com.ukcorp.ieum.pill.dto.response.PillTimeGetResponseDto;
import com.ukcorp.ieum.pill.dto.response.TotalPillGetResponseDto;
import com.ukcorp.ieum.pill.entity.PillInfo;
import com.ukcorp.ieum.pill.entity.PillTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PillInfoMapper {

  @Mapping(source = "pill.careInfo.careNo", target = "careNo")
  PillInfoGetResponseDto pillInfoToResponseDto(PillInfo pill);

  TotalPillGetResponseDto pillInfoAndPillTimesToResponseDto(PillInfoGetResponseDto pillInfo, List<PillTimeGetResponseDto> pillTimes);

}
