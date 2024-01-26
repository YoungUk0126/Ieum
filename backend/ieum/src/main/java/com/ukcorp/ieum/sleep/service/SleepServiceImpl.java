package com.ukcorp.ieum.sleep.service;

import com.ukcorp.ieum.sleep.dto.request.SleepInsertRequestDto;
import com.ukcorp.ieum.sleep.dto.request.SleepUpdateRequestDto;
import com.ukcorp.ieum.sleep.dto.response.SleepResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SleepServiceImpl implements SleepService {
  @Override
  public SleepResponseDto getSleep(Long sleepInfoNo) throws Exception {
    return null;
  }

  @Transactional
  @Override
  public void deleteSleep(Long sleepInfoNo) {

  }

  @Transactional
  @Override
  public void registSleep(SleepInsertRequestDto sleep) throws Exception {

  }

  @Transactional
  @Override
  public void modifySleep(SleepUpdateRequestDto sleep) throws Exception {

  }
}
