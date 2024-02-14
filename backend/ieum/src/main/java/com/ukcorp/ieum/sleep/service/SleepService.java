package com.ukcorp.ieum.sleep.service;

import com.ukcorp.ieum.sleep.dto.request.SleepInsertRequestDto;
import com.ukcorp.ieum.sleep.dto.request.SleepUpdateRequestDto;
import com.ukcorp.ieum.sleep.dto.response.SleepResponseDto;

public interface SleepService {
  SleepResponseDto getSleep() throws Exception;

  void deleteSleep(Long sleepInfoNo) throws Exception;

  void registSleep(SleepInsertRequestDto sleep) throws Exception;

  void modifySleep(SleepUpdateRequestDto sleep) throws Exception;
}
