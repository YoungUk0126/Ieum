package com.ukcorp.ieum.event.repository;

import com.ukcorp.ieum.event.entity.ReguralEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<ReguralEvent, Long> {
}
