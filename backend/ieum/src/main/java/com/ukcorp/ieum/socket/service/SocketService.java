package com.ukcorp.ieum.socket.service;

public interface SocketService {

  void sendPillDataToIot(Long careNo);

  void sendEventDataToIot(Long careNo);

  void sendSleepDataToIot(Long careNo);

  void sendMealDataToIot(Long careNo);

  void sendMessageToIot(Long careNo);

  void sendCallAlertToIot();

  void sendCallStopAlertToIot();

  void sendAnyEventToIot(Long careNo);
}
