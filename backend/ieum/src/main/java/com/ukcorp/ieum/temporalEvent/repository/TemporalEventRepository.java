package com.ukcorp.ieum.temporalEvent.repository;

import com.ukcorp.ieum.temporalEvent.entity.TemporalEvent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  TemporalEventRepository extends JpaRepository<TemporalEvent, Long> {
  List<TemporalEvent> findByCareInfoCareNo(Long careNo);
}
