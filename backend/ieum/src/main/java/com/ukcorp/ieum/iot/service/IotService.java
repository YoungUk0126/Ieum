package com.ukcorp.ieum.iot.service;

import org.springframework.stereotype.Service;

@Service
public interface IotService {

    void registDevice(String code);
    void updateDeviceInfo(String code);
}
