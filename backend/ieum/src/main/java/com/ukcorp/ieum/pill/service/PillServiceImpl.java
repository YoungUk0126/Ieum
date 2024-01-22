package com.ukcorp.ieum.pill.service;

import com.ukcorp.ieum.member.repository.MemberRepository;
import com.ukcorp.ieum.pill.dto.request.PillInfoRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PillServiceImpl implements PillService{
//    Repository 의존성 생성자 주입
    private final MemberRepository repo;

//    약 정보 넣기
    @Override
    public int insertPill(PillInfoRequestDto pillInfo, List<String> pillTimeDate, List<String> pillTimeTime) {
//        mapper로 Entity로 변환
//        repo.save()로 넣기
        return 0;
    }
}
