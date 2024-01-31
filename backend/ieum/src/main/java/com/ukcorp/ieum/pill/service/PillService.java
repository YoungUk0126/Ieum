package com.ukcorp.ieum.pill.service;

import com.ukcorp.ieum.pill.dto.request.PillInfoInsertRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillInfoUpdateRequestDto;
import com.ukcorp.ieum.pill.dto.response.PillInfoJoinResponseDto;

import java.util.List;

public interface PillService {

//    약 정보 등록
    void insertPill(PillInfoInsertRequestDto pillInfoDto) throws Exception;
    PillInfoJoinResponseDto getPillInfo(Long pillInfoNo) throws Exception;
    List<PillInfoJoinResponseDto> getAllPillInfo(Long careNo) throws Exception;
    void updatePill(PillInfoUpdateRequestDto pillInfoDto) throws Exception;
    void deletePillInfo(Long pillInfoNo) throws Exception;
    void deletePillTime(Long pillTimeNo) throws Exception;
}
