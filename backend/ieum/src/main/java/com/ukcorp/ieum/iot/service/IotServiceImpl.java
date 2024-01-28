package com.ukcorp.ieum.iot.service;

import com.ukcorp.ieum.iot.entity.SerialCode;
import com.ukcorp.ieum.iot.entity.Usable;
import com.ukcorp.ieum.iot.repository.IotRepository;
import com.ukcorp.ieum.member.entity.Member;
import com.ukcorp.ieum.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IotServiceImpl implements IotService {

    private final IotRepository iotRepository;
    private final MemberRepository memberRepository;

    /**
     * 작성자 : 이성목
     * 해당 디바이스가 사용 중인지 확인
     *
     * @param code
     * @return
     */
    @Override
    public boolean activeCheck(String code) {
        Optional<SerialCode> serialCode = iotRepository.searchSerialCode(code);
        try {
            SerialCode device = serialCode.get();
            if (device.getUsable().equals(Usable.ACTIVE)) { //기기가 사용 중이라면
                return true;
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();    //해당 코드의 기기를 찾을 수 없을 때
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
    public void registSerialCode(String code, String userId) {
        Optional<Member> byMemberId = memberRepository.findByMemberId(userId);

        if (!activeCheck(code)) {
            Optional<SerialCode> serialCode = iotRepository.searchSerialCode(code);

            serialCode.get().updateUsableActice();  //기기 상태 활성화

            byMemberId.get().updateSerialCode(code);    //회원에게 기기 할당
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
    public void updateSerialCode(String code, String userId) {
        Member member = memberRepository.findByMemberId(userId).get();
        String oldCode = member.getSerialCode();

        if (!activeCheck(code) && activeCheck(oldCode)) {

            Optional<SerialCode> oldDevice = iotRepository.searchSerialCode(oldCode);
            Optional<SerialCode> newDevice = iotRepository.searchSerialCode(code);

            oldDevice.get().updateUsableInactive(); //사용하던 기기 비활성화

            member.updateSerialCode(code);  //기기 정보 업데이트
            newDevice.get().updateUsableActice();   //새 기기 활성화

        } else {
            throw new RuntimeException("기기를 변경할 수 없다");
        }
    }
}
