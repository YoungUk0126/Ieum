package com.ukcorp.ieum.iot.service;

import org.springframework.stereotype.Service;

@Service
public interface IotService {

    void registDevice(String memberId, String code);
    void updateDeviceInfo(String memberId, String code);
}
