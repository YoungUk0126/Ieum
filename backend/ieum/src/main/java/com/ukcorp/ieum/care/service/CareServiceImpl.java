package com.ukcorp.ieum.care.service;

import com.ukcorp.ieum.care.dto.request.CareInsertRequestDto;
import com.ukcorp.ieum.care.dto.request.CareUpdateRequestDto;
import com.ukcorp.ieum.care.dto.response.CareGetResponseDto;
import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.mapper.CareMapper;
import com.ukcorp.ieum.care.repository.CareRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CareServiceImpl implements CareService{

    private final CareRepository careRepository;
    private final CareMapper careMapper;

    @Transactional
    @Override
    public void insertCareInfo(CareInsertRequestDto careDto) throws Exception {
        try {
            CareInfo care = careMapper.careInsertRequestDtoToCareInfo(careDto);
            careRepository.save(care);
        } catch (DataIntegrityViolationException e) {
            log.debug("입력 오류!");
            throw new Exception("입력 오류!");
        }
    }

    @Override
    public CareGetResponseDto getCareInfo(Long careNo) throws Exception {
        try {
            CareInfo care = careRepository.findById(careNo).orElse(null);
            if(care != null) {
                return careMapper.careInfoToCareGetResponseDto(care);
            } else {
                log.debug("등록된 피보호자가 없습니다!");
                throw new Exception("등록된 피보호자가 없습니다!");
            }
        } catch (RuntimeException e) {
            log.debug("조회 오류!");
            throw new Exception("조회 오류!");
        }
    }

    @Transactional
    @Override
    public void updateCareInfo(CareUpdateRequestDto careDto) throws Exception {
        try {
            CareInfo check = careRepository.findById(careDto.getCareNo()).orElse(null);
            if(check == null) {
                throw new Exception("수정하려는 피보호자 조회 오류");
            } else {
                CareInfo care = careMapper.CareUpdateRequestDtoToCareInfo(careDto);
                careRepository.save(care);
            }
        } catch (DataIntegrityViolationException e) {
            log.debug("수정 오류!");
            throw new Exception("수정 오류!");
        }
    }

    @Transactional
    @Override
    public void deleteCareInfo(Long careNo) throws Exception{
        try{
            careRepository.deleteById(careNo);
        } catch (EmptyResultDataAccessException e) {
            log.debug("삭제 오류!");
            throw new Exception("삭제 오류!");
        }
    }
}
