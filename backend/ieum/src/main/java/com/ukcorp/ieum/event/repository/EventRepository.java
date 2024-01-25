package com.ukcorp.ieum.event.repository;

import com.ukcorp.ieum.event.entity.RegularEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<RegularEvent, Long> {
    List<RegularEvent> findAllByCareInfo_CareNo(Long careNo);
}
