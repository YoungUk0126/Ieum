package com.ukcorp.ieum.pill.service;

import com.ukcorp.ieum.pill.dto.request.PillInfoRequestDto;

import java.util.List;

public interface PillService {

//    약 정보 등록
    int insertPill(PillInfoRequestDto pillInfo, List<String> pillTimeDate, List<String> pillTimeTime);
}
