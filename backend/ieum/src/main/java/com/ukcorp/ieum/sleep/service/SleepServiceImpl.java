package com.ukcorp.ieum.sleep.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.sleep.dto.request.SleepInsertRequestDto;
import com.ukcorp.ieum.sleep.dto.request.SleepUpdateRequestDto;
import com.ukcorp.ieum.sleep.dto.response.SleepResponseDto;
import com.ukcorp.ieum.sleep.entity.SleepInfo;
import com.ukcorp.ieum.sleep.mapper.SleepMapper;
import com.ukcorp.ieum.sleep.repository.SleepRepository;
import com.ukcorp.ieum.temporalEvent.entity.TemporalEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    Optional<SleepInfo> tempSleep = sleepRepository.findById(sleepInfoNo);
    if(tempSleep.isPresent()){
      return sleepMapper.SleepInfoToResponseDto(tempSleep.get());
    }else{
      log.debug("존재하지 않는 취침 시간 데이터입니다.");
      throw new Exception("존재하지 않는 취침 시간 데이터입니다.");
    }
  }

  @Transactional
  @Override
  public void deleteSleep(Long sleepInfoNo) {
    sleepRepository.deleteById(sleepInfoNo);
  }

  @Transactional
  @Override
  public void registSleep(SleepInsertRequestDto sleep) throws Exception {
    try{
      Optional<CareInfo> careGet = careRepository.findById(sleep.getCareNo());
      if(careGet.isEmpty()){
        throw new Exception("보호자 정보 조회 오류");
      }

      CareInfo care = careGet.get();
      SleepInfo entity = sleepMapper
              .sleepInsertRequestDtoAndCareInfoToSleepInfo(sleep,care);
      sleepRepository.save(entity);

    } catch (RuntimeException e) {
      throw new Exception("입력 오류!");
    }

  }

  @Transactional
  @Override
  public void modifySleep(SleepUpdateRequestDto sleep) throws Exception {
    try{
      Optional<CareInfo> careGet = careRepository.findById(sleep.getCareNo());
      if(careGet.isEmpty()){
        throw new Exception("보호자 정보 조회 오류");
      }
      CareInfo care = careGet.get();
      SleepInfo entity = sleepMapper
              .sleepUpdateRequestDtoAndCareInfoToSleepInfo(sleep,care);
      sleepRepository.save(entity);

    } catch (RuntimeException e) {
      throw new Exception("입력 오류!");
    }

  }
  }
}
