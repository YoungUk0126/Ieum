package com.ukcorp.ieum.care.mapper;

import com.ukcorp.ieum.care.dto.request.CareInsertRequestDto;
import com.ukcorp.ieum.care.dto.request.CareUpdateRequestDto;
import com.ukcorp.ieum.care.dto.response.CareGetResponseDto;
import com.ukcorp.ieum.care.entity.CareInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CareMapper {

    CareGetResponseDto careInfoToCareGetResponseDto(CareInfo care);

    @Mapping(target = "careNo", ignore = true)
    @Mapping(target = "careImage", ignore = true)
    CareInfo careInsertRequestDtoToCareInfo(CareInsertRequestDto careInsertRequestDto);

    @Mapping(target = "careImage", ignore = true)
    CareInfo CareUpdateRequestDtoToCareInfo(CareUpdateRequestDto careDto);
}
