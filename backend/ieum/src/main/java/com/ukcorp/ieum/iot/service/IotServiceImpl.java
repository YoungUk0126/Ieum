package com.ukcorp.ieum.iot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ukcorp.ieum.api.dto.MessageDTO;
import com.ukcorp.ieum.api.service.NaverService;
import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.chat.entity.ChatHistory;
import com.ukcorp.ieum.chat.repository.ChatHistoryRepository;
import com.ukcorp.ieum.iot.entity.SerialCode;
import com.ukcorp.ieum.iot.entity.Usable;
import com.ukcorp.ieum.iot.repository.IotRepository;
import com.ukcorp.ieum.jwt.JwtUtil;
import com.ukcorp.ieum.member.entity.Member;
import com.ukcorp.ieum.member.repository.MemberRepository;
import com.ukcorp.ieum.socket.service.SocketService;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IotServiceImpl implements IotService {

  private final IotRepository iotRepository;
  private final MemberRepository memberRepository;
  private final CareRepository careRepository;
  private final ChatHistoryRepository chatRepository;
  private final SocketService socketService;
  private final NaverService naverService;

  /**
   * 화재 시 메시지 보내는 메서드
   */
  @Override
  public void sendFireSms(String serial)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
    Member member = memberRepository.findBySerialCode(serial)
        .orElseThrow(() -> new NoSuchElementException("NOT FOUND MEMBER"));
    String phone = member.getMemberPhone().replaceAll("-", "");

    MessageDTO sendMessage = MessageDTO.builder()
        .to(phone)
        .content("[이음] 보호자의 자택에 화재가 발생했습니다").build();

    naverService.sendSms(sendMessage);
  }

  @Override
  public void checkInfo(String serial) {
    CareInfo careInfo = careRepository.findCareInfoByCareSerial(serial)
        .orElseThrow(() -> new NoSuchElementException("NOT FOUND CARE INFO"));
    Long careNo = careInfo.getCareNo();

    socketService.sendMealDataToIot(careNo);
    socketService.sendSleepDataToIot(careNo);
    socketService.sendEventDataToIot(careNo);
    socketService.sendPillDataToIot(careNo);
    socketService.sendAnyEventToIot(careNo);
  }


  /**
   * 작성자 : 이성목 해당 디바이스가 사용 중인지 확인
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
   * 작성자 : 서다찬 해당 디바이스가 사용 중인지 확인(토큰)
   *
   * @param
   * @return
   */
  @Override
  public String activeCheckToken() throws Exception {

    Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo에 없어요"));

    CareInfo care = careRepository.findCareInfoByCareNo(careNo)
        .orElseThrow(() -> new Exception("존재하지 않는 사용자입니다"));

    SerialCode device = iotRepository.searchBySerialCode(care.getCareSerial()).orElseThrow(
        () -> new NoSuchElementException("해당 코드의 기기를 찾을 수 없습니다"));

    if (!device.getUsable().equals(Usable.ACTIVE)) { //기기가 사용 중이라면
      return "사용이 시작되지 않은 기기입니다";
    }else{
      ChatHistory chat = chatRepository.findFirstByCareInfoCareNoOrderByChatDateDesc(careNo);
      if(chat == null){
        return "아직 대화를 시작하지 않았습니다.";
      }else{
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(chat.getChatDate(), now);
        long daysAgo = duration.toDays();
        if (daysAgo == 0) {
          long hoursAgo = duration.toHours();
          return hoursAgo + "시간 전에 마지막으로 대화하셨습니다";
        } else {
          return daysAgo + "일 전에 마지막으로 대화하셨습니다";
        }
      }
    }
  }


  /**
   * 시리얼 번호 확인
   *
   * @param careNo
   * @return
   */
  @Override
  public boolean checkSerialCode(String serial) {
    SerialCode serialCode = iotRepository.searchBySerialCode(serial).orElseThrow(
        () -> new NoSuchElementException());

    return serialCode.getUsable().equals(Usable.ACTIVE);
  }



  /**
   * 해당 careNo에 해당하는 기기 시리얼 번호 확인
   *
   * @param careNo
   * @return
   */
  @Override
  public String getSerialCode(Long careNo) {
    SerialCode device = iotRepository.findById(careNo).orElseThrow(
        () -> new NoSuchElementException("해당 코드의 기기를 찾을 수 없습니다"));

    if (device.getUsable().equals(Usable.ACTIVE)) { //기기가 사용 중이라면
      return device.getSerialCode();
    }
    return null;
  }

  @Override
  public void sendCloseAlert() {
    socketService.sendCallStopAlertToIot();
  }

  /**
   * 작성자 : 이성목 기기 등록하는 서비스 메서드
   *
   * @param code
   * @param userId
   */
  @Override
  @Transactional
  public void registSerialCode(String code, String userId) {

    Member member = memberRepository.findByMemberId(userId).orElseThrow(
        () -> new NoSuchElementException());

    CareInfo careInfo = member.getCareInfo();

    if (!activeCheck(code)) {
      SerialCode serialCode = iotRepository.searchBySerialCode(code).orElseThrow(
          () -> new NoSuchElementException());

      serialCode.updateUsableActice();  //기기 상태 활성화
      careInfo.updateSerialCode(code);    //회원에게 기기 할당
      member.updateSerial(code);

    } else {
      throw new RuntimeException("이미 사용중인 기기입니다");
    }
  }

  /**
   * 작성자 : 이성목 기기 수정하는 서비스 메서드
   *
   * @param code
   * @param userId
   */
  @Override
  @Transactional
  public void updateSerialCode(String code, String userId) {
    Member member = memberRepository.findByMemberId(userId).orElseThrow(
        () -> new NoSuchElementException());

    CareInfo careInfo = careRepository.findCareInfoByCareSerial(code).orElseThrow(
        () -> new NoSuchElementException());

    String oldCode = careInfo.getCareSerial();

    if (!activeCheck(code) && activeCheck(oldCode)) {

      SerialCode oldDevice = iotRepository.searchBySerialCode(oldCode).orElseThrow(
          () -> new NoSuchElementException());

      SerialCode newDevice = iotRepository.searchBySerialCode(code).orElseThrow(
          () -> new NoSuchElementException());
      ;

      oldDevice.updateUsableInactive(); //사용하던 기기 비활성화

      careInfo.updateSerialCode(code);  //기기 정보 업데이트
      newDevice.updateUsableActice();   //새 기기 활성화
      member.updateSerial(code);

    } else {
      throw new RuntimeException("기기를 변경할 수 없다");
    }
  }

}
