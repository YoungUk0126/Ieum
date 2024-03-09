package com.ukcorp.ieum.iot.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface IotService {

  void sendFireSms(String serial)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException;

  void checkInfo(String serial);

  boolean activeCheck(String code) throws Exception;

  String activeCheckToken() throws Exception;

  void registSerialCode(String code, String userId);

  void updateSerialCode(String code, String userId);

  String getSerialCode(Long careNo);

  void sendCloseAlert();

  boolean checkSerialCode(String serial);

}
