package com.ukcorp.ieum.pill.repository;

import com.ukcorp.ieum.pill.entity.PillInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PillInfoRepository extends JpaRepository< PillInfo, Long> {
    @Query( "select p from PillInfo p join fetch p.pillTimes")
    Optional<PillInfo> findByPillInfoIdFetchJoin( Long pillInfoId );

//    Care에 Fetch Join문을 작성해야 합니다.
    List<PillInfo> findByCareInfo_CareNo(Long careNo);
}
