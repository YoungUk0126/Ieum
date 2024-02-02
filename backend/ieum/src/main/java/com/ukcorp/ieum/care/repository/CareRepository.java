package com.ukcorp.ieum.care.repository;

import com.ukcorp.ieum.care.entity.CareInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareRepository extends JpaRepository<CareInfo, Long> {

    boolean existsByCarePhone(String phone);
}
