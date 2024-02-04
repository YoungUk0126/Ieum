package com.ukcorp.ieum.socket.mapper;

import com.ukcorp.ieum.sleep.entity.SleepInfo;
import com.ukcorp.ieum.socket.dto.response.SleepResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SleepSocketMapper {

    SleepResponseDto InfoToDto(SleepInfo sleepInfo);
}
