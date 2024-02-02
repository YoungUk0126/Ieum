package com.ukcorp.ieum.iot.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.event.repository.EventRepository;
import com.ukcorp.ieum.iot.entity.SerialCode;
import com.ukcorp.ieum.iot.entity.Usable;
import com.ukcorp.ieum.iot.repository.IotRepository;
import com.ukcorp.ieum.meal.repository.MealRepository;
import com.ukcorp.ieum.member.entity.Member;
import com.ukcorp.ieum.member.repository.MemberRepository;
import com.ukcorp.ieum.message.repository.MessageRepository;
import com.ukcorp.ieum.pill.repository.PillInfoRepository;
import com.ukcorp.ieum.sleep.repository.SleepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IotServiceImpl implements IotService {

  private final IotRepository iotRepository;
//  private final MemberRepository memberRepository;
  private final CareRepository careRepository;
  private final PillInfoRepository pillInfoRepository;
  private final EventRepository eventRepository;
  private final SleepRepository sleepRepository;
  private final MealRepository mealRepository;
  private MessageRepository messageRepository;


  /**
   * 작성자 : 이성목
   * 해당 디바이스가 사용 중인지 확인
   *
   * @param code
   * @return
   */
  @Override
  public boolean activeCheck(String code) {
    SerialCode device = iotRepository.searchBySerialCode(code).orElseThrow(
            () -> new NoSuchElementException("해당 코드의 기기를 찾을 수 없습니다"));

    if (device.getUsable().equals(Usable.ACTIVE)) { //기기가 사용 중이라면
      return true;
    }
    return false;
  }

  /**
   * 작성자 : 이성목
   * 기기 등록하는 서비스 메서드
   *
   * @param code
   * @param userId
   */
  @Override
  @Transactional
  public void registSerialCode(String code, String userId) {

//    Member member = memberRepository.findByMemberId(userId).orElseThrow(
//            () -> new NoSuchElementException());

    CareInfo careInfo = careRepository.findCareInfoByCareSerial(code).orElseThrow(
            () -> new NoSuchElementException());
    if (!activeCheck(code)) {
      SerialCode serialCode = iotRepository.searchBySerialCode(code).orElseThrow(
              () -> new NoSuchElementException());

      serialCode.updateUsableActice();  //기기 상태 활성화
      careInfo.updateSerialCode(code);    //회원에게 기기 할당

    } else {
      throw new RuntimeException("이미 사용중인 기기입니다");
    }
  }

  /**
   * 작성자 : 이성목
   * 기기 수정하는 서비스 메서드
   *
   * @param code
   * @param userId
   */
  @Override
  @Transactional
  public void updateSerialCode(String code, String userId) {
//    Member member = memberRepository.findByMemberId(userId).orElseThrow(
//            () -> new NoSuchElementException());

    CareInfo careInfo = careRepository.findCareInfoByCareSerial(code).orElseThrow(
            () -> new NoSuchElementException());

    String oldCode = careInfo.getCareSerial();

    if (!activeCheck(code) && activeCheck(oldCode)) {

      SerialCode oldDevice = iotRepository.searchBySerialCode(oldCode).orElseThrow(
              () -> new NoSuchElementException());

      SerialCode newDevice = iotRepository.searchBySerialCode(code).orElseThrow(
              () -> new NoSuchElementException());

      oldDevice.updateUsableInactive(); //사용하던 기기 비활성화

      careInfo.updateSerialCode(code);  //기기 정보 업데이트
      newDevice.updateUsableActice();   //새 기기 활성화

    } else {
      throw new RuntimeException("기기를 변경할 수 없다");
    }
  }

  /**
   * 작성자 : 이성목
   *
   * 기기 엔드포인트 저장하는 서비스 메서드
   * @param code
   * @param endPoint
   */
  @Override
  @Transactional
  public void updateEndPoint(String code, String endPoint) {
    SerialCode device = iotRepository.searchBySerialCode(code).orElseThrow(
            () -> new NoSuchElementException());
    device.updateEndpoint(endPoint);
  }

  /**
   * 작성자 : 이성목
   * 복용약 정보 데이터를
   * @param careNo
   */
  @Override
  public void sendPillDataToIot(Long careNo) {

  }

  @Override
  public void sendEventDateToIot(Long careNo) {

  }

  @Override
  public void sendSleepDateToIot(Long careNo) {

  }

  @Override
  public void sendMealDateToIot(Long careNo) {

  }

  @Override
  public void sendMessageToIot(Long careNo) {

  }

  @Override
  public void sendCallAlertToIot(Long careNo) {

  }
}
