package com.ukcorp.ieum.pill.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.pill.dto.request.PillInfoRequestDto;
import com.ukcorp.ieum.pill.dto.request.PillTimeRequestDto;
import com.ukcorp.ieum.pill.dto.response.PillInfoResponseDto;
import com.ukcorp.ieum.pill.dto.response.PillTimeResponseDto;
import com.ukcorp.ieum.pill.entity.PillInfo;
import com.ukcorp.ieum.pill.entity.PillTime;
import com.ukcorp.ieum.pill.repository.PillInfoRepository;
import com.ukcorp.ieum.pill.repository.PillTimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PillServiceImpl implements PillService {
    //    Repository 의존성 생성자 주입
    private final PillInfoRepository pillInfoRepo;


    //    약 정보 넣기
    @Override
    public int insertPill(PillInfoRequestDto pillInfo) {
//        mapper로 Entity로 변환
//        repo.save()로 넣기
        return 0;
    }

    @Override
    public PillInfoResponseDto getPillInfo(Long pillInfoNo) {
//        PillInfo로 바로 받으면 Null값이 넘어올 때 바로 에러를 뱉기 때문에 Optional로 받아야함
        Optional<PillInfo> byPillInfoNo = pillInfoRepo.findByPillInfoIdFetchJoin(pillInfoNo);
//        리턴하기 위한 PillInfoResponseDto 선언
        PillInfoResponseDto pillInfoResponseDto = null;

//        DB에서 받아온 PillInfo가 있다면
        if (byPillInfoNo.isPresent()) {
//            PillInfo 데이터 형으로 옮긴 후
            PillInfo pillInfo = byPillInfoNo.get();
//            Builder를 사용해 사용자에게 데이터를 전송할 DTO로 옮긴다
            pillInfoResponseDto = PillInfoResponseDto.builder()
                    .pillName(pillInfo.getPillName())
                    .startDate(pillInfo.getPillStartDate())
                    .endDate(pillInfo.getPillEndDate())
                    .pillMethod(pillInfo.getPillMethod().toString())
                    .pillTimes( new ArrayList<>())
//                    .pillTimes( pillInfo.getPillTimes().stream().map( PillTimeResponseDto::new).collect(Collectors.toList()))
                    .build();
            for(PillTime p: pillInfo.getPillTimes()) {
                pillInfoResponseDto.getPillTimes().add(new PillTimeResponseDto(p));
            }
        }
        return pillInfoResponseDto;
    }

    @Override
    public List<PillInfoResponseDto> getAllPillInfo(Long careNo) {
        List<PillInfo> pillInfos = pillInfoRepo.findByCareInfo_CareNo(careNo);

//        mapper로 DTO로 변환 후 리턴
        return null;
    }

    @Override
    public void deletePill(Long pillInfoNo) {
        pillInfoRepo.deleteById(pillInfoNo);
    }
}
