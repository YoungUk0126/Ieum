package com.ukcorp.ieum.pill.service;

import com.ukcorp.ieum.pill.dto.request.PillInfoInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillInfoUpdateRequestDto;
import com.ukcorp.ieum.pill.dto.response.PillInfoGetResponseDto;
import com.ukcorp.ieum.pill.dto.response.TotalPillGetResponseDto;

import java.util.List;

public interface PillService {

//    약 정보 등록
    void insertPill(PillInfoInsertRequestDto pillInfo) throws Exception;
    TotalPillGetResponseDto getPillInfo(Long pillInfoNo) throws Exception;
    List<TotalPillGetResponseDto> getAllPillInfo(Long careNo) throws Exception;
    void updatePill(PillInfoUpdateRequestDto pillInfo) throws Exception;
    void deletePillInfo(Long pillInfoNo) throws Exception;
    void deletePillTime(Long pillTimeNo) throws Exception;
}
