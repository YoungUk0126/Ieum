package com.ukcorp.ieum.message.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.jwt.JwtUtil;
import com.ukcorp.ieum.message.dto.request.MessageInsertRequestDto;
import com.ukcorp.ieum.message.dto.request.MessageUpdateRequestDto;
import com.ukcorp.ieum.message.dto.response.MessageResponseDto;
import com.ukcorp.ieum.message.entity.Message;
import com.ukcorp.ieum.message.mapper.MessageMapper;
import com.ukcorp.ieum.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {

  private final MessageMapper messageMapper;
  private final MessageRepository messageRepository;
  private final CareRepository careRepository;

  @Override
  public List<MessageResponseDto> getList() throws Exception {
    try {
      Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo에 없어요"));

      List<Message> list = messageRepository.findByCareInfoCareNo(careNo);

      return messageMapper.MessageToMessageResponseDto(list);
    } catch (RuntimeException e) {
      log.debug("조회하는데 오류가 있습니다");
      throw new Exception("조회 오류!");
    }
  }

  @Override
  public MessageResponseDto getDetail(Long messageNo) throws Exception {
    try {
      Message message = messageRepository.findById(messageNo).orElseThrow(() -> new NoSuchElementException("존재하지 않는 메세지입니다."));

      return messageMapper.MessageToResponseDto(message);

    } catch (RuntimeException e) {
      log.debug("조회하는데 오류가 있습니다");
      throw new Exception("조회 오류!");
    }
  }


  @Override
  @Transactional
  public void deleteMessage(Long messageNo) throws Exception {
    try {
      messageRepository.deleteById(messageNo);
    } catch (EmptyResultDataAccessException e) {
      log.debug("삭제 오류");
      throw new Exception("삭제 오류!");
    }
  }


  @Override
  @Transactional
  public void registMessage(MessageInsertRequestDto message) throws Exception {
    try {
      Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo에 없어요"));

      CareInfo care = careRepository.findById(careNo).orElseThrow(() -> new NoSuchElementException("보호자 정보 조회 오류."));

      Message entity = messageMapper
              .messageInsertRequestDtoAndCareInfoToMessage(message, care);
      messageRepository.save(entity);

    } catch (DataIntegrityViolationException e) {
      log.debug("등록 오류");
      throw new Exception("등록 오류!");
    }


  }

  @Override
  @Transactional
  public void modifyMessage(MessageUpdateRequestDto message) throws Exception {
    try {
      Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo에 없어요"));

      CareInfo care = careRepository.findById(careNo).orElseThrow(() -> new NoSuchElementException("보호자 정보 조회 오류."));

      if(!messageRepository.existsById(message.getMessageNo())){
        throw new NoSuchElementException("존재하지 않는 메세지 입니다.");
      }

      Message entity = messageMapper
              .messageUpdateRequestDtoAndCareInfoToMessage(message, care);
      messageRepository.save(entity);

    } catch (DataIntegrityViolationException e) {
      log.debug("수정하는데 오류가 있습니다");
      throw new Exception("수정 오류!");
    }
  }
}
