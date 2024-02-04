package com.ukcorp.ieum.sleep.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.jwt.JwtUtil;
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

import java.time.LocalTime;
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
  public SleepResponseDto getSleep() throws Exception {
    try {
      Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo가 없어요"));
//      조회할 데이터가 있으면 가져오고 없으면 예외 처리
      SleepInfo sleep = sleepRepository.findByCareInfo_CareNo(careNo).orElseThrow(() -> new NoSuchElementException("존재하지 않는 취침 시간 데이터입니다."));

      Long start = sleep.getSleepStartTime();
      Long end = sleep.getSleepEndTime();

      long sH = start / 3600;
      start %= 3600;
      long sM = start / 60;

      long eH = end / 3600;
      end %= 3600;
      long eM = end / 60;
      return SleepResponseDto.builder()
              .sleepInfoNo(sleep.getSleepInfoNo())
              .careNo(sleep.getCareInfo().getCareNo())
              .sleepStartTime(LocalTime.of((int) sH, (int) sM, 0))
              .sleepEndTime(LocalTime.of((int) eH, (int) eM, 0))
              .build();
//      return sleepMapper.SleepInfoToResponseDto(sleep);
    } catch (RuntimeException e) {
      log.debug("조회하는데 오류가 있습니다");
      throw new Exception("조회 오류");
    }
  }

  @Transactional
  @Override
  public void deleteSleep(Long sleepInfoNo) throws Exception {
    try {
      sleepRepository.deleteById(sleepInfoNo);
    } catch (EmptyResultDataAccessException e) {
      log.debug("삭제 오류");
      throw new Exception("삭제 오류!");
    }
  }

  @Transactional
  @Override
  public void registSleep(SleepInsertRequestDto sleep) throws Exception {
    try {
      Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo가 없어요"));
//      피보호자 정보가 있으면 가져오고 없으면 예외 처리
      CareInfo care = careRepository.findById(careNo).orElseThrow(() -> new NoSuchElementException("존재하지 않는 피보호자 데이터입니다."));
//      SleepInfo entity = sleepMapper
//              .sleepInsertRequestDtoAndCareInfoToSleepInfo(sleep, care);
      SleepInfo entity = SleepInfo.builder()
              .careInfo(care)
              .sleepStartTime(sleep.getSleepStartTime().getHour() * 3600L + sleep.getSleepStartTime().getMinute() * 60L)
              .sleepEndTime(sleep.getSleepEndTime().getHour() * 3600L + sleep.getSleepEndTime().getMinute() * 60L)
              .build();
      sleepRepository.save(entity);

    } catch (DataIntegrityViolationException e) {
      log.debug("등록 오류!");
      throw new Exception("등록 오류!");
    }

  }

  @Transactional
  @Override
  public void modifySleep(SleepUpdateRequestDto sleep) throws Exception {
    try {
      Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo가 없어요"));
//      피보호자 정보가 있으면 가져오고 없으면 예외 처리
      CareInfo care = careRepository.findById(careNo).orElseThrow(() -> new NoSuchElementException("존재하지 않는 피보호자 데이터입니다."));
//      수정할 끼니시간 정보가 있으면 가져오고 없으면 예외 처리
      if(!sleepRepository.existsById(sleep.getSleepInfoNo())){
        throw new NoSuchElementException("존재하지 않는 취침 시간 정보입니다.");
      }
//      SleepInfo entity = sleepMapper
//              .sleepUpdateRequestDtoAndCareInfoToSleepInfo(sleep, care);
      SleepInfo entity = SleepInfo.builder()
              .careInfo(care)
              .sleepStartTime(sleep.getSleepStartTime().getHour() * 3600L + sleep.getSleepStartTime().getMinute() * 60L)
              .sleepEndTime(sleep.getSleepEndTime().getHour() * 3600L + sleep.getSleepEndTime().getMinute() * 60L)
              .build();
      sleepRepository.save(entity);
      sleepRepository.save(entity);

    } catch (DataIntegrityViolationException e) {
      log.debug("수정 오류!");
      throw new Exception("수정 오류!");
    }

  }
}
