package com.ukcorp.ieum.temporalEvent.repository;

import com.ukcorp.ieum.temporalEvent.entity.TemporalEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemporalEventRepository extends JpaRepository<TemporalEvent, Long> {
  List<TemporalEvent> findByCareInfoCareNo(Long careNo);
}
