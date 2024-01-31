package com.ukcorp.ieum.pill.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.pill.dto.request.PillInfoInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillInfoUpdateRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillTimeInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillTimeUpdateRequestDto;
import com.ukcorp.ieum.pill.dto.response.PillInfoGetResponseDto;
import com.ukcorp.ieum.pill.dto.response.PillTimeGetResponseDto;
import com.ukcorp.ieum.pill.dto.response.TotalPillGetResponseDto;
import com.ukcorp.ieum.pill.entity.PillInfo;
import com.ukcorp.ieum.pill.entity.PillMethod;
import com.ukcorp.ieum.pill.entity.PillTime;
import com.ukcorp.ieum.pill.mapper.PillInfoMapper;
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
import java.util.Arrays;
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

  private final PillInfoMapper pillInfoMapper;
  private final PillTimeMapper pillTimeMapper;

  private final CareRepository careRepository;


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

      System.out.println(pillInfo.toString());

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
  //    약 정보 넣기

  @Override
  public TotalPillGetResponseDto getPillInfo(Long pillInfoNo) throws Exception {
    try {
//        PillInfo로 받고, Null값이 넘어올 때 바로 Exception
      PillInfo pillInfo = pillInfoRepository.findById(pillInfoNo).orElseThrow(() -> new NoSuchElementException("존재하지 않는 약정보입니다."));
//      System.out.println("PillInfoEntity : " + pillInfo.toString());
//        이건 목록이 없으면 없는대로 빈 리스트 반환
      List<PillTime> pillTimes = pillTimeRepository.findPillTimesByPillInfo_PillInfoNo(pillInfoNo);
//        리턴하기 위한 PillInfoResponseDto 선언
      PillInfoGetResponseDto pillInfoGetResponseDto = pillInfoMapper.pillInfoToResponseDto(pillInfo);
//      System.out.println("PillInfoGetResponseDto : " + pillInfoGetResponseDto.toString());
      List<PillTimeGetResponseDto> pillTimeGetResponseDtos = pillTimeMapper.pillTimesToResponseDto(pillInfo.getPillTimes());

      TotalPillGetResponseDto totalPillGetResponseDto = TotalPillGetResponseDto.builder()
              .pillInfo(pillInfoGetResponseDto)
              .pillTimes(pillTimeGetResponseDtos)
              .build();
//      for(PillTimeGetResponseDto dto: pillTimeGetResponseDtos) {
//        System.out.println("pillTimeGetResponseDto : "+dto.toString());
//      }

      return totalPillGetResponseDto;
    } catch (RuntimeException e) {
      log.debug("조회하는데 오류가 있습니다");
      throw new Exception("조회 오류!");
    }

  }

  @Override
  public List<TotalPillGetResponseDto> getAllPillInfo(Long careNo) throws Exception {
    try {
      List<PillInfo> pillInfos = pillInfoRepository.findByCareInfo_CareNo(careNo);
//      클라이언트에게 보낼 List미리 선언
      List<TotalPillGetResponseDto> totalPills = new ArrayList<>();
      for (PillInfo pillInfo : pillInfos) {
//          해당 pillInfo를 DTO로 변환
        PillInfoGetResponseDto pillInfoGetResponseDto = pillInfoMapper.pillInfoToResponseDto(pillInfo);
//          pillInfo의 PK에 속해있는 pillTime 리스트들 받아오기
        List<PillTime> pillTimes = pillTimeRepository.findPillTimesByPillInfo_PillInfoNo(pillInfo.getPillInfoNo());
//        pillTimeDTO List로 변환
        List<PillTimeGetResponseDto> pillTimeGetResponseDtos = pillTimeMapper.pillTimesToResponseDto(pillTimes);
//        totalPill 리스트에 변환한 totalPillGetResponseDto를 넣어줌
        totalPills.add(pillInfoMapper.pillInfoAndPillTimesToResponseDto(pillInfoGetResponseDto, pillTimeGetResponseDtos));
      }

      return totalPills;

    } catch (RuntimeException e) {
      log.debug("조회하는데 오류가 있습니다");
      throw new Exception("조회 오류!");
    }
  }

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
