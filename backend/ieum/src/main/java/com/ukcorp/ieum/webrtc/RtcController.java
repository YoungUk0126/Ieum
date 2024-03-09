package com.ukcorp.ieum.webrtc;

import com.ukcorp.ieum.care.dto.response.CareGetResponseDto;
import com.ukcorp.ieum.care.service.CareService;
import com.ukcorp.ieum.iot.service.IotService;
import com.ukcorp.ieum.socket.service.SocketService;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class RtcController {

  private final SocketService socketService;
  private final IotService iotService;

  private final CareService careService;

  @Value("${openvidu.url}")
  private String OPENVIDU_URL;

  @Value("${openvidu.secret}")
  private String OPENVIDU_SECRET;

  private OpenVidu openvidu;

  @PostConstruct
  public void init() {

    this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
  }

  /**
   * param으로 session에 대한 속성을 입력 받고 openvidu로 생성된 sessionId를 넘겨줌
   *
   * @param params The Session properties
   * @return The Session ID
   */
  @PostMapping
  public ResponseEntity<String> initializeSession()
      throws Exception {
    CareGetResponseDto care = careService.getCareInfo();
    String serialCode = care.getCareSerial();
    SessionProperties properties = new SessionProperties.Builder().customSessionId(serialCode)
        .build();
    Session session = openvidu.createSession(properties);
    return new ResponseEntity<>(session.getSessionId(), HttpStatus.OK);
  }

  /**
   * 처음 RTC 연결을 여는 컨트롤러, 연결이 되면 openVidu에 세션을 열고 토큰을 반환함
   *
   * @param sessionId The Session in which to create the Connection
   * @param params    The Connection properties
   * @return The Token associated to the Connection
   */
  @PostMapping("/connections/{serialNo}")
  public ResponseEntity<String> createConnection(@PathVariable("serialNo") String serialNo,
      @RequestBody(required = false) Map<String, Object> params)
      throws OpenViduJavaClientException, OpenViduHttpException {

    Session session = openvidu.getActiveSession(serialNo);
    if (session == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
    Connection connection = session.createConnection(properties);
    try {
      // 소켓을 통해서 iot측으로 call 연결 전송
      socketService.sendCallAlertToIot();
    } catch (Exception e) {
      // Exception 상황은 careNo을 못찾는 상황
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(connection.getToken(), HttpStatus.OK);
  }

  // 피보호자 (이음이) 기기 측에서 접속할때는 세션 정보 없이 serial 번호로 접속한다
  @PostMapping("/care")
  public ResponseEntity<String> initializeSessionForCare(
      @RequestBody(required = false) Map<String, Object> params)
      throws OpenViduJavaClientException, OpenViduHttpException {
    SessionProperties properties = SessionProperties.fromJson(params).build();
    Session session = openvidu.createSession(properties);
    return new ResponseEntity<>(session.getSessionId(), HttpStatus.OK);
  }
  // 피보호자측 세션 연결
  @PostMapping("/care/connections/{serialNo}")
  public ResponseEntity<String> createCareConnection(@PathVariable("serialNo") String serialNo,
                                                 @RequestBody(required = false) Map<String, Object> params)
          throws OpenViduJavaClientException, OpenViduHttpException {

    Session session = openvidu.getActiveSession(serialNo);
    if (session == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
    Connection connection = session.createConnection(properties);

    return new ResponseEntity<>(connection.getToken(), HttpStatus.OK);
  }
}
