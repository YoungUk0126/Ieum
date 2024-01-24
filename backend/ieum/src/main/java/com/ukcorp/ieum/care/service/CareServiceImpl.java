package com.ukcorp.ieum.care.service;

import com.ukcorp.ieum.care.dto.request.CareInsertRequestDto;
import com.ukcorp.ieum.care.dto.request.CareUpdateRequestDto;
import com.ukcorp.ieum.care.dto.response.CareGetResponseDto;
import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.mapper.CareMapper;
import com.ukcorp.ieum.care.repository.CareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CareServiceImpl implements CareService{

    private final CareRepository careRepository;
    private final CareMapper careMapper;

    @Transactional
    @Override
    public void insertCareInfo(CareInsertRequestDto careDto) throws Exception {

    }

    @Override
    public CareGetResponseDto getCareInfo(Long careNo) throws Exception {
        Optional<CareInfo> tempCareInfo = careRepository.findById(careNo);
        if(tempCareInfo.isPresent()) {
            CareInfo care = tempCareInfo.get();
            CareGetResponseDto careDto = careMapper.careInfoToCareGetResponseDto(care);
            return careDto;
        } else {
            throw new Exception("피보호자 데이터가 존재하지 않습니다.");
        }
    }

    @Transactional
    @Override
    public void updateCareInfo(CareUpdateRequestDto careDto) throws Exception {

    }

    @Transactional
    @Override
    public void deleteCareInfo(Long careNo) {

    }
}
