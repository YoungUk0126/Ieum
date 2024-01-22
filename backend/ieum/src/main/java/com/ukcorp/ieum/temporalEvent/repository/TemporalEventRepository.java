package com.ukcorp.ieum.schedule.repository;

import com.ukcorp.ieum.schedule.entity.TemporalEvent;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface  TemporalEventRepository extends JpaRepository<TemporalEvent, Long> {
  List<TemporalEvent> findByCareInfoCareNo(Long careNo);
}
