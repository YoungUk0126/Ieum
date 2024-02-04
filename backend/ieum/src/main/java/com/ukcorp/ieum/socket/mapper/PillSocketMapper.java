package com.ukcorp.ieum.socket.mapper;

import com.ukcorp.ieum.pill.entity.PillInfo;
import com.ukcorp.ieum.pill.entity.PillTime;
import com.ukcorp.ieum.socket.dto.PillTimeDto;
import com.ukcorp.ieum.socket.dto.PillInfoDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PillSocketMapper {

    @Named("T2D")
    PillTimeDto timeToDto(PillTime pillTime);

    @Named("I2D")
    @IterableMapping(qualifiedByName = "T2D")
    PillInfoDto infoToDto(PillInfo pillInfo);

    @IterableMapping(qualifiedByName = "I2D")
    List<PillInfoDto> infoesToDtoes(List<PillInfo> pillInfo);
}
