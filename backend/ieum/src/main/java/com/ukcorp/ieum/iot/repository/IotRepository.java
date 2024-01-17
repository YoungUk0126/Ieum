package com.ukcorp.ieum.iot.repository;

import com.ukcorp.ieum.iot.entity.SerialCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IotRepository extends JpaRepository<SerialCode, Long> {
}
