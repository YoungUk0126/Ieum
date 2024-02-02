package com.ukcorp.ieum.iot.service;

public interface IotService {

    boolean activeCheck(String code);

    void registSerialCode(String code, String userId);

    void updateSerialCode(String code, String userId);

    void updateEndPoint(String code, String endPoint);

    void sendPillDataToIot(Long careNo);

    void sendEventDateToIot(Long careNo);

    void sendSleepDateToIot(Long careNo);

    void sendMealDateToIot(Long careNo);

    void sendMessageToIot(Long careNo);

    void sendCallAlertToIot(Long careNo);
}
