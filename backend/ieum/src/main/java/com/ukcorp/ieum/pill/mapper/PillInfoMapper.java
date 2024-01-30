package com.ukcorp.ieum.pill.mapper;


import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.pill.dto.request.PillInfoInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillInfoUpdateRequestDto;
import com.ukcorp.ieum.pill.dto.response.PillInfoGetResponseDto;
import com.ukcorp.ieum.pill.entity.PillInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PillInfoMapper {

//  @Mapping(target = "pillInfoNo", ignore = true)
//  @Mapping(target = "pill.careNo", ignore = true)
//  @Mapping(source = "care", target = "careInfo")
//  PillInfo pillInfoInsertDtoToPillInfo(PillInfoInsertRequestDto pill, CareInfo care);
//
//
//  @Mapping(target = "pill.careNo", ignore = true)
//  @Mapping(source = "care", target = "careInfo")
//  PillInfo pillInfoUpdateDtoToPillInfo(PillInfoUpdateRequestDto pill, CareInfo care);
//
//
//  @Mapping(source = "pill.careInfo.careNo", target = "careNo")
//  PillInfoGetResponseDto pillInfoToResponseDto(PillInfo pill);
}
