package com.ukcorp.ieum.message.mapper;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.message.dto.request.MessageInsertRequestDto;
import com.ukcorp.ieum.message.dto.request.MessageUpdateRequestDto;
import com.ukcorp.ieum.message.dto.response.MessageResponseDto;
import com.ukcorp.ieum.message.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

  List<MessageResponseDto> MessageToMessageResponseDto(List<Message> message);

  @Mapping(target = "messageNo", ignore = true)
  @Mapping(target = "message.careNo", ignore = true)
  @Mapping(source = "care", target = "careInfo")
  Message messageInsertRequestDtoAndCareInfoToMessage(MessageInsertRequestDto message, CareInfo care);


  @Mapping(target = "messageNo", ignore = true)
  @Mapping(target = "message.careNo", ignore = true)
  @Mapping(source = "care", target = "careInfo")
  Message messageUpdateRequestDtoAndCareInfoToMessage(MessageUpdateRequestDto message, CareInfo care);

  @Mapping(target = "careNo", source = "message.careInfo.careNo")
  MessageResponseDto MessageToResponseDto(Message message);
}
