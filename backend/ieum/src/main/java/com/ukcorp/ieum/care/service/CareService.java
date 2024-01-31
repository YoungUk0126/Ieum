package com.ukcorp.ieum.care.service;

import com.ukcorp.ieum.care.dto.request.CareInsertRequestDto;
import com.ukcorp.ieum.care.dto.request.CareUpdateRequestDto;
import com.ukcorp.ieum.care.dto.response.CareGetResponseDto;

public interface CareService {
    void insertCareInfo(CareInsertRequestDto careDto) throws Exception;
    CareGetResponseDto getCareInfo() throws Exception;
    void updateCareInfo(CareUpdateRequestDto careDto) throws Exception;
    void deleteCareInfo() throws Exception;

}
