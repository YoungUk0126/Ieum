package com.ukcorp.ieum.pill.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.pill.dto.request.PillInfoInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillInfoUpdateRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillTimeInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillTimeUpdateRequestDto;
import com.ukcorp.ieum.pill.dto.response.PillInfoJoinResponseDto;
import com.ukcorp.ieum.pill.entity.PillInfo;
import com.ukcorp.ieum.pill.entity.PillMethod;
import com.ukcorp.ieum.pill.entity.PillTime;
import com.ukcorp.ieum.pill.mapper.PillTimeMapper;
import com.ukcorp.ieum.pill.repository.PillInfoRepository;
import com.ukcorp.ieum.pill.repository.PillTimeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PillServiceImpl implements PillService {
  //    Repository 의존성 생성자 주입
  private final PillInfoRepository pillInfoRepository;
  private final PillTimeRepository pillTimeRepository;

  private final PillTimeMapper pillTimeMapper;

  private final CareRepository careRepository;


  //    약 정보 넣기
  @Transactional
  @Override
  public void insertPill(PillInfoInsertRequestDto pillInfoDto) throws Exception {
    try {
      CareInfo care = careRepository.findById(pillInfoDto.getCareNo()).orElseThrow(() -> new NoSuchElementException("존재하지 않는 피보호자 정보입니다."));
      PillInfo pillInfo = PillInfo.builder()
              .pillName(pillInfoDto.getPillName())
              .careInfo(care)
              .pillStartDate(pillInfoDto.getPillStartDate())
              .pillEndDate(pillInfoDto.getPillEndDate())
//            Enum 자동 매핑 되는지 확인할 것
              .pillMethod(PillMethod.valueOf(pillInfoDto.getPillMethod()))
              .pillDate(pillInfoDto.getPillDate())
              .build();

      pillInfoRepository.save(pillInfo);

      List<PillTime> pillTimes = new ArrayList<>();

      for (PillTimeInsertRequestDto dto : pillInfoDto.getPillTimes()) {
        PillTime pillTime = pillTimeMapper.pillTimeInsertRequestDtoToPillTime(dto, pillInfo);
        pillTimes.add(pillTime);
      }

//    repository로 저장하기!!!!
      pillTimeRepository.saveAll(pillTimes);
    } catch (DataIntegrityViolationException e) {
      log.debug("등록 오류!");
      throw new Exception("등록 오류!");
    }

  }

//  약정보 조회
  @Override
  public PillInfoJoinResponseDto getPillInfo(Long pillInfoNo) throws Exception {
    try {
//        PillInfo로 받고, Null값이 넘어올 때 바로 Exception
      PillInfo pillInfo = pillInfoRepository.findById(pillInfoNo).orElseThrow(() -> new NoSuchElementException("존재하지 않는 약정보입니다."));
      PillInfoJoinResponseDto pillInfoJoinResponseDto = new PillInfoJoinResponseDto(pillInfo);

      return pillInfoJoinResponseDto;
    } catch (RuntimeException e) {
      log.debug("조회하는데 오류가 있습니다");
      throw new Exception("조회 오류!");
    }

  }
  
//  피보호자가 등록한 약정보 리스트 조회
  @Override
  public List<PillInfoJoinResponseDto> getAllPillInfo(Long careNo) throws Exception {
    try {
      List<PillInfo> pillInfos = pillInfoRepository.findByCareInfo_CareNo(careNo);
//      클라이언트에게 보낼 List미리 선언
      List<PillInfoJoinResponseDto> pillDtos = new ArrayList<>();
      for (PillInfo pillInfo : pillInfos) {
        pillDtos.add(new PillInfoJoinResponseDto(pillInfo));
      }
      return pillDtos;
    } catch (RuntimeException e) {
      log.debug("조회하는데 오류가 있습니다");
      throw new Exception("조회 오류!");
    }
  }

//  약 정보 수정
  @Transactional
  @Override
  public void updatePill(PillInfoUpdateRequestDto pillInfoDto) throws Exception {
    try {
      CareInfo care = careRepository.findById(pillInfoDto.getCareNo()).orElseThrow(() -> new NoSuchElementException("존재하지 않는 피보호자 정보입니다."));
      PillInfo pillInfo = PillInfo.builder()
//              update라 PK추가해줌
              .pillInfoNo(pillInfoDto.getPillInfoNo())
              .pillName(pillInfoDto.getPillName())
              .careInfo(care)
              .pillStartDate(pillInfoDto.getPillStartDate())
              .pillEndDate(pillInfoDto.getPillEndDate())
//            Enum 자동 매핑 되는지 확인할 것
              .pillMethod(PillMethod.valueOf(pillInfoDto.getPillMethod()))
              .pillDate(pillInfoDto.getPillDate())
              .build();

      List<PillTime> pillTimes = new ArrayList<>();

      for (PillTimeUpdateRequestDto dto : pillInfoDto.getPillTimes()) {
        PillTime pillTime = pillTimeMapper.pillTimeUpdateRequestDtoToPillTime(dto, pillInfo);
        pillTimes.add(pillTime);
      }

//    repository로 저장하기!!!!
      pillInfoRepository.save(pillInfo);
      pillTimeRepository.saveAll(pillTimes);
    } catch (DataIntegrityViolationException e) {
      log.debug("수정 오류!");
      throw new Exception("수정 오류!");
    }
  }
  
//약 정보 삭제 및 약 정보에 관련된 약복용 시간도 삭제
  @Transactional
  @Override
  public void deletePillInfo(Long pillInfoNo) throws Exception {
    try {
      pillTimeRepository.deleteAllByPillInfo_PillInfoNo(pillInfoNo);
      pillInfoRepository.deleteById(pillInfoNo);
    } catch (EmptyResultDataAccessException e) {
      log.debug("PILL_INFO 삭제 오류");
      throw new Exception("PILL_INFO 삭제 오류!");
    }
  }
//약 복용 시간만 삭제
  @Transactional
  @Override
  public void deletePillTime(Long pillTimeNo) throws Exception {
    try {
      pillTimeRepository.deleteById(pillTimeNo);
    } catch (EmptyResultDataAccessException e) {
      log.debug("PILL_TIME 삭제 오류");
      throw new Exception("PILL_TIME 삭제 오류!");
    }
  }

}
