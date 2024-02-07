package com.ukcorp.ieum.iot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ukcorp.ieum.api.dto.MessageDTO;
import com.ukcorp.ieum.api.service.NaverService;
import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.iot.entity.SerialCode;
import com.ukcorp.ieum.iot.entity.Usable;
import com.ukcorp.ieum.iot.repository.IotRepository;
import com.ukcorp.ieum.member.entity.Member;
import com.ukcorp.ieum.member.repository.MemberRepository;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

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
    private final NaverService naverService;

    /**
     * 화재 시 메시지 보내는 메서드
     */
    @Override
    public void sendFireSms(String serial) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
        Member member = memberRepository.findBySerialCode(serial)
                .orElseThrow(() -> new NoSuchElementException("NOT FOUND MEMBER"));
        String phone = member.getMemberPhone().replaceAll("-", "");

        MessageDTO sendMessage = MessageDTO.builder()
                .to(phone)
                .content("[이음] 보호자의 자택에 화재가 발생했습니다").build();

        naverService.sendSms(sendMessage);
    }
    

    /**
     * 해당 careNo에 해당하는 기기 시리얼 번호 확인
     *
     * @param code
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

        Member member = memberRepository.findByMemberId(userId).orElseThrow(
                () -> new NoSuchElementException());

        CareInfo careInfo = careRepository.findCareInfoByCareSerial(code).orElseThrow(
                () -> new NoSuchElementException());
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
            ;

            oldDevice.updateUsableInactive(); //사용하던 기기 비활성화

            careInfo.updateSerialCode(code);  //기기 정보 업데이트
            newDevice.updateUsableActice();   //새 기기 활성화

        } else {
            throw new RuntimeException("기기를 변경할 수 없다");
        }
    }

}
