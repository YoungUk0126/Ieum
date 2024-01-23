package com.ukcorp.ieum.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ukcorp.ieum.api.dto.MessageDTO;
import com.ukcorp.ieum.api.dto.SmsResponseDTO;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Naver 서비스 인터페이스
 *
 * @author : dachan
 * @fileName : NaverServices
 * @since : 01/20/24
 */

@Service
public interface NaverService {
  SmsResponseDTO sendSms(MessageDTO messageDto)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException;

  String getSTT(MultipartFile file);
}