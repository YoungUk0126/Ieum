package com.ukcorp.ieum.iot.service;

public interface IotService {

  boolean activeCheck(String code);

  void registSerialCode(String code, String userId);

  void updateSerialCode(String code, String userId);

  String getSerialCode(Long careNo);
}
