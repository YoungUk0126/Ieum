package com.ukcorp.ieum.care.service;

import com.ukcorp.ieum.care.dto.request.CareInsertRequestDto;
import com.ukcorp.ieum.care.dto.request.CareUpdateRequestDto;
import com.ukcorp.ieum.care.dto.response.CareGetResponseDto;

public interface CareService {
    void insertCareInfo(CareInsertRequestDto careDto) throws Exception;
    CareGetResponseDto getCareInfo(Long careNo) throws Exception;
    void updateCareInfo(CareUpdateRequestDto careDto) throws Exception;
    void deleteCareInfo(Long careNo) throws Exception;

}
