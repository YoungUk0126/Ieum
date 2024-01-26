package com.ukcorp.ieum.message.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.message.dto.request.MessageInsertRequestDto;
import com.ukcorp.ieum.message.dto.request.MessageUpdateRequestDto;
import com.ukcorp.ieum.message.dto.response.MessageResponseDto;
import com.ukcorp.ieum.message.entity.Message;
import com.ukcorp.ieum.message.mapper.MessageMapper;
import com.ukcorp.ieum.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {

  private final MessageMapper messageMapper;
  private final MessageRepository messageRepository;
  private final CareRepository careRepository;

  @Override
  public List<MessageResponseDto> getList(Long careNo) throws Exception {
    List<Message> list = messageRepository.findByCareInfoCareNo(careNo);
    if (list == null || list.isEmpty()) {
      log.debug("등록된 메세지이 없습니다");
      throw new Exception("등록된 메세지이 없습니다.");
    }
    return messageMapper.MessageToMessageResponseDto(list);
  }

  @Override
  public MessageResponseDto getDetail(Long messageNo) throws Exception {
    Optional<Message> message = messageRepository.findById(messageNo);
    if (message.isPresent()) {
      return messageMapper.MessageToResponseDto(message.get());
    } else {
      log.debug("존재하지 않는 메세지입니다.");
      throw new Exception("존재하지 않는 메세지입니다.");
    }
  }


  @Override
  @Transactional
  public void deleteMessage(Long messageNo) {
    messageRepository.deleteById(messageNo);
  }


  @Override
  @Transactional
  public void registMessage(MessageInsertRequestDto message) throws Exception {
    try {
      Optional<CareInfo> careGet = careRepository.findById(message.getCareNo());
      if (careGet.isEmpty()) {
        log.debug("보호자 정보 조회 오류.");
        throw new Exception("보호자 정보 조회 오류");
      }

      CareInfo care = careGet.get();
      Message entity = messageMapper
              .messageInsertRequestDtoAndCareInfoToMessage(message, care);
      messageRepository.save(entity);

    } catch (RuntimeException e) {
      log.debug("입력 오류!ㄴ");
      throw new Exception("입력 오류!");
    }

  }

  @Override
  @Transactional
  public void modifyMessage(MessageUpdateRequestDto message) throws Exception {
    try {
      Optional<CareInfo> careGet = careRepository.findById(message.getCareNo());
      if (careGet.isEmpty()) {
        log.debug("보호자 정보 조회 오류.");
        throw new Exception("보호자 정보 조회 오류");
      }
      CareInfo care = careGet.get();
      Message entity = messageMapper
              .messageUpdateRequestDtoAndCareInfoToMessage(message, care);
      messageRepository.save(entity);

    } catch (RuntimeException e) {
      log.debug("입력 오류!");
      throw new Exception("입력 오류!");
    }

  }
}
