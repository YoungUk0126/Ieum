package com.ukcorp.ieum.webrtc;

import com.ukcorp.ieum.iot.service.IotService;
import com.ukcorp.ieum.jwt.JwtUtil;
import io.openvidu.java.client.Connection;
import io.openvidu.java.client.ConnectionProperties;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class RtcController {

  private final IotService iotService;

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
    Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo에 없어요"));
    System.out.println(careNo);
    String serialCode = iotService.getSerialCode(careNo);
    SessionProperties properties = new SessionProperties.Builder().customSessionId(serialCode).build();
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
}
