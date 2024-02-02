package com.ukcorp.ieum.care.service;

import com.ukcorp.ieum.care.dto.request.CareInsertRequestDto;
import com.ukcorp.ieum.care.dto.request.CarePhoneRequestDto;
import com.ukcorp.ieum.care.dto.request.CareUpdateRequestDto;
import com.ukcorp.ieum.care.dto.response.CareGetResponseDto;
import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.entity.Gender;
import com.ukcorp.ieum.care.mapper.CareMapper;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CareServiceImpl implements CareService {

  private final CareRepository careRepository;
  private final CareMapper careMapper;

  @Override
  public CareGetResponseDto getCareInfo() throws Exception {
    try {
      Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo가 없어요"));
//      피보호자 정보가 있으면 가져오고 없으면 예외 처리
      CareInfo care = careRepository.findById(careNo).orElseThrow(() -> new NoSuchElementException("존재하지 않는 피보호자 데이터입니다."));
      return careMapper.careInfoToCareGetResponseDto(care);
    } catch (RuntimeException e) {
      log.debug("조회 오류!");
      throw new Exception("조회 오류!");
    }
  }

  @Transactional
  @Override
  public void updateCareInfo(CareUpdateRequestDto careDto) throws Exception {
    try {
//      피보호자 정보가 있으면 가져오고 없으면 예외 처리
      Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo가 없어요"));
//        수정하려는 피보호자 데이터가 있는지 확인
      if(!careRepository.existsById(careNo)){
        throw new NoSuchElementException("피보호자 정보가 없습니다.");
      }
//      이미지는 가져와서 여기 빌더에 넣어!
      CareInfo care = CareInfo.builder()
              .careNo(careNo)
              .careAddr(careDto.getCareAddr())
              .careBirth(careDto.getCareBirth())
              .careGender(Gender.valueOf(careDto.getCareGender()))
              .carePhone(careDto.getCarePhone())
              .careImage(careDto.getCareImage())
              .careSerial(careDto.getCareSerial())
              .build();
      careRepository.save(care);
    } catch (DataIntegrityViolationException e) {
      log.debug("수정 오류!");
      throw new Exception("수정 오류!");
    }
  }

  @Transactional
  @Override
  public void deleteCareInfo() throws Exception {
    try {
//      피보호자 정보가 있으면 가져오고 없으면 예외 처리
      Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo가 없어요"));
      careRepository.deleteById(careNo);
    } catch (EmptyResultDataAccessException e) {
      log.debug("삭제 오류!");
      throw new Exception("삭제 오류!");
    }
  }

  @Override
  public boolean isExistsMemberPhone(CarePhoneRequestDto phoneDto) {
    return careRepository.existsByCarePhone(phoneDto.getPhone());
  }
}
