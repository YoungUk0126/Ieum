package com.ukcorp.ieum.pill.service;

import com.ukcorp.ieum.pill.dto.request.PillInfoInsertRequestDto;
import com.ukcorp.ieum.pill.dto.response.PillInfoGetResponseDto;

import java.util.List;

public interface PillService {

//    약 정보 등록
    int insertPill(PillInfoInsertRequestDto pillInfo);
    PillInfoGetResponseDto getPillInfo(Long pillInfoNo);
    List<PillInfoGetResponseDto> getAllPillInfo(Long careNo);

    void deletePill(Long pillInfoNo);
}
