package com.ukcorp.ieum.api.config;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class NaverConfig {

    @Value("${naver-cloud-sms.secretKey}")
    private String secretKey;

    @Value("${naver-cloud-sms.serviceId}")
    private String serviceId;

    @Value("${naver-cloud-sms.accessKey}")
    private String accessKey;

    @Value("${naver-cloud-stt.secret}")
    private String ttsSecret;

    @Value("${naver-cloud-stt.id}")
    private String ttsId;


    /**
     * STT 사용을 위한 HttpURLConnection 생성
     * @return HttpURLConnection
     */
    public HttpURLConnection getSttHttpURLConnection() throws IOException {
        String language = "Kor";        // 언어 코드 ( Kor, Jpn, Eng, Chn )
        String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
        URL url = new URL(apiURL);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setUseCaches(false);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/octet-stream");
        conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", ttsId);
        conn.setRequestProperty("X-NCP-APIGW-API-KEY", ttsSecret);
        return conn;
    }

    /**
     * Sms 사용을 위한 HttpHeaders 생성
     * @return HttpHeaders
     */
    public HttpHeaders getSmsHttpHeaders() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        Long time = System.currentTimeMillis();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", accessKey);
        headers.set("x-ncp-apigw-signature-v2", makeSignature(time));

        return headers;
    }

    /**
     * 문자 전송을 위해 필요한 시그니처 생성 로직
     * @param time
     * @return String
     */
    public String makeSignature(Long time)
            throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {

        // signature에서 사용하게 될 구분자와 HTTP 방식, 앱키등을 등록해서 인코딩된 값으로 시그니쳐를 생성하기
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/"+ this.serviceId+"/messages";
        String timestamp = time.toString();
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));

        return Base64.encodeBase64String(rawHmac);
    }
}
