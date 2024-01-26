package com.ukcorp.ieum.sleep.repository;


import com.ukcorp.ieum.temporalEvent.entity.TemporalEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SleepRepository extends JpaRepository<TemporalEvent, Long> {
}
