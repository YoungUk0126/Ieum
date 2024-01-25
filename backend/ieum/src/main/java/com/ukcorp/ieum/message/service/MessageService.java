package com.ukcorp.ieum.message.service;

import com.ukcorp.ieum.message.dto.request.MessageInsertRequestDto;
import com.ukcorp.ieum.message.dto.request.MessageUpdateRequestDto;
import com.ukcorp.ieum.message.dto.response.MessageResponseDto;

import java.util.List;

public interface MessageService {
  List<MessageResponseDto> getList(Long careNo) throws Exception;
  MessageResponseDto getDetail(Long messageNo) throws Exception;
  void deleteMessage(Long messageNo);
  void registMessage(MessageInsertRequestDto message) throws Exception;

  void modifyMessage(MessageUpdateRequestDto message) throws Exception;
}
