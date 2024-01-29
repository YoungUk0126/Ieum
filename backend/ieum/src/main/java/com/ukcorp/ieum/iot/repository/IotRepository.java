package com.ukcorp.ieum.iot.repository;

import com.ukcorp.ieum.iot.entity.SerialCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IotRepository extends JpaRepository<SerialCode, Long> {

    Optional<SerialCode> searchSerialCode(String serialCode);

}
