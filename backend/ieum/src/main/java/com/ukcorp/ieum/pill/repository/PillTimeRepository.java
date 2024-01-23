package com.ukcorp.ieum.pill.repository;

import com.ukcorp.ieum.pill.entity.PillInfo;
import com.ukcorp.ieum.pill.entity.PillTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PillTimeRepository extends JpaRepository<PillTime, Long> {

//    PillInfoNo라는 FK로 PillTime 리스트를 받아오는 메서드인데... 맞게 쓴지 모르겠음
    List<PillTime> findPillTimesByPillInfo_PillInfoNo(Long pillInfoNo);
}
