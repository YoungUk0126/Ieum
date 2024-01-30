package com.ukcorp.ieum.pill.service;

import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.pill.dto.request.PillInfoInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillInfoUpdateRequestDto;
import com.ukcorp.ieum.pill.dto.response.PillInfoGetResponseDto;
import com.ukcorp.ieum.pill.dto.response.PillTimeGetResponseDto;
import com.ukcorp.ieum.pill.dto.response.TotalPillGetResponseDto;
import com.ukcorp.ieum.pill.entity.PillInfo;
import com.ukcorp.ieum.pill.entity.PillTime;
import com.ukcorp.ieum.pill.mapper.PillInfoMapper;
import com.ukcorp.ieum.pill.mapper.PillTimeMapper;
import com.ukcorp.ieum.pill.repository.PillInfoRepository;
import com.ukcorp.ieum.pill.repository.PillTimeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


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
  public void insertPill(PillInfoInsertRequestDto pillInfo) {
//        mapper로 Entity로 변환
//        repo.save()로 넣기
  }
  //    약 정보 넣기

  @Override
  public TotalPillGetResponseDto getPillInfo(Long pillInfoNo) throws Exception {
    try {
//        PillInfo로 받고, Null값이 넘어올 때 바로 Exception
      PillInfo pillInfo = pillInfoRepository.findByPillInfoIdFetchJoin(pillInfoNo).orElseThrow(() -> new NoSuchElementException("존재하지 않는 약정보입니다."));
//        이건 목록이 없으면 없는대로 빈 리스트 반환
      List<PillTime> pillTimes = pillTimeRepository.findPillTimesByPillInfo_PillInfoNo(pillInfoNo);
//        리턴하기 위한 PillInfoResponseDto 선언
      PillInfoGetResponseDto pillInfoGetResponseDto = pillInfoMapper.pillInfoToResponseDto(pillInfo);
      List<PillTimeGetResponseDto> pillTimeGetResponseDtos = pillTimeMapper.pillTimesToResponseDto(pillTimes);


//            Builder를 사용해 사용자에게 데이터를 전송할 DTO로 옮긴다
//      pillInfoGetResponseDto = PillInfoGetResponseDto.builder()
//              .pillInfoNo(pillInfo.getPillInfoNo())
//              .careNo(pillInfo.getCareInfo().getCareNo())
//              .pillName(pillInfo.getPillName())
//              .startDate(pillInfo.getPillStartDate())
//              .endDate(pillInfo.getPillEndDate())
//              .pillMethod(pillInfo.getPillMethod().toString())
//              .pillDate(pillInfo.getPillDate())
//              .build();


//      totalPillGetResponseDto = TotalPillGetResponseDto.builder()
//              .pillInfo(pillInfoGetResponseDto)
//              .pillTimes(pillTimeGetResponseDtos)
//              .build();

      return pillInfoMapper.pillInfoAndPillTimesToResponseDto(pillInfoGetResponseDto, pillTimeGetResponseDtos);
    } catch (RuntimeException e) {
      log.debug("조회하는데 오류가 있습니다");
      throw new Exception("조회 오류!");
    }

  }

  @Override
  public List<TotalPillGetResponseDto> getAllPillInfo(Long careNo) throws Exception {
    try {
      List<PillInfo> pillInfos = pillInfoRepository.findByCareInfo_CareNo(careNo);
      for (PillInfo pillInfo : pillInfos) {
        List<PillTime> pillTimes = pillTimeRepository.findPillTimesByPillInfo_PillInfoNo(pillInfo.getPillInfoNo());
      }

    } catch (RuntimeException e) {
      log.debug("조회하는데 오류가 있습니다");
      throw new Exception("조회 오류!");
    }


//        mapper로 DTO로 변환 후 리턴
    return null;
  }

  @Transactional
  @Override
  public void updatePill(PillInfoUpdateRequestDto pillInfo) throws Exception {

  }

  @Transactional
  @Override
  public void deletePillInfo(Long pillInfoNo) throws Exception {

  }

  @Transactional
  @Override
  public void deletePillTime(Long pillTimeNo) throws Exception {

  }


  @Override
  public void deletePill(Long pillInfoNo) {
    pillInfoRepository.deleteById(pillInfoNo);
  }
}
