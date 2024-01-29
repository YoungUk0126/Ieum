package com.ukcorp.ieum.sleep.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.sleep.dto.request.SleepInsertRequestDto;
import com.ukcorp.ieum.sleep.dto.request.SleepUpdateRequestDto;
import com.ukcorp.ieum.sleep.dto.response.SleepResponseDto;
import com.ukcorp.ieum.sleep.entity.SleepInfo;
import com.ukcorp.ieum.sleep.mapper.SleepMapper;
import com.ukcorp.ieum.sleep.repository.SleepRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SleepServiceImpl implements SleepService {

  private final SleepRepository sleepRepository;
  private final CareRepository careRepository;
  private final SleepMapper sleepMapper;
  @Override
  public SleepResponseDto getSleep(Long sleepInfoNo) throws Exception {

    try {
      SleepInfo sleep = sleepRepository.findById(sleepInfoNo).orElseThrow(() -> new NoSuchElementException("존재하지 않는 취침 시간 데이터입니다."));
    } catch (RuntimeException e) {
      log.debug("조회하는데 오류가 있습니다");
      throw new Exception("조회 오류");
    }
  }

  @Transactional
  @Override
  public void deleteSleep(Long sleepInfoNo) throws Exception {
    try{
      sleepRepository.deleteById(sleepInfoNo);
    }catch (EmptyResultDataAccessException e) {
      log.debug("삭제 오류");
      throw new Exception("삭제 오류!");
    }
  }

  @Transactional
  @Override
  public void registSleep(SleepInsertRequestDto sleep) throws Exception {
    try{
      CareInfo care = careRepository.findById(sleep.getCareNo()).orElseThrow(() -> new NoSuchElementException("존재하지 않는 피보호자 데이터입니다."));
      SleepInfo entity = sleepMapper
              .sleepInsertRequestDtoAndCareInfoToSleepInfo(sleep,care);
      sleepRepository.save(entity);

    } catch (DataIntegrityViolationException e) {
      log.debug("등록 오류!");
      throw new Exception("등록 오류!");
    }

  }

  @Transactional
  @Override
  public void modifySleep(SleepUpdateRequestDto sleep) throws Exception {
    try{
      CareInfo care = careRepository.findById(sleep.getCareNo()).orElseThrow(() -> new NoSuchElementException("존재하지 않는 피보호자 데이터입니다."));
      SleepInfo entity = sleepMapper
              .sleepUpdateRequestDtoAndCareInfoToSleepInfo(sleep,care);
      sleepRepository.save(entity);

    } catch (DataIntegrityViolationException e) {
      log.debug("수정 오류!");
      throw new Exception("수정 오류!");
    }

  }
}
