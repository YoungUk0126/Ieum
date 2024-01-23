package com.ukcorp.ieum.pill.service;

import com.ukcorp.ieum.pill.dto.request.PillInfoRequestDto;
import com.ukcorp.ieum.pill.dto.response.PillInfoResponseDto;

import java.util.List;

public interface PillService {

//    약 정보 등록
    int insertPill(PillInfoRequestDto pillInfo);
    PillInfoResponseDto getPillInfo(Long pillInfoNo);
    List<PillInfoResponseDto> getAllPillInfo(Long careNo);

    void deletePill(Long pillInfoNo);
}
