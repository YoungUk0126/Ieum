package com.ukcorp.ieum.sleep.repository;


import com.ukcorp.ieum.sleep.entity.SleepInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SleepRepository extends JpaRepository<SleepInfo, Long> {
  Optional<SleepInfo> findByCareInfo_CareNo(Long careNo);
}
