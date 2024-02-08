package com.ukcorp.ieum.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukcorp.ieum.api.dto.TextRequestDto;
import com.ukcorp.ieum.api.service.ChatGPTService;
import com.ukcorp.ieum.api.service.GoogleService;
import com.ukcorp.ieum.api.service.NaverService;
import com.ukcorp.ieum.chat.dto.EmotionDto;
import com.ukcorp.ieum.chat.service.ChatHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/ieum")
@Slf4j
public class IeumAPIController {
    private final GoogleService googleService;
    private final NaverService naverService;
    private final ChatGPTService chatGPTService;
    private final ChatHistoryService chatHistoryService;
    private final ObjectMapper objectMapper;

    @PostMapping("/speak")
    public ResponseEntity<byte[]> testToSpeech(@RequestBody TextRequestDto text) {
        try {
            byte[] audioData = googleService.convertTextToSpeech(text.getText());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "output.mp3");

            // Http response로 mp3 파일 전송
            return new ResponseEntity<>(audioData, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "output.mp3");

            return new ResponseEntity<>(null, headers, HttpStatus.OK);
        }
    }

    @PostMapping("/{serial}")
    public ResponseEntity<byte[]> getSTT(@PathVariable("serial") String serial,
                                         @RequestParam("file") MultipartFile file) {
        try {
            Long careNo = chatHistoryService.getCareNoBySerial(serial);
            String stt = naverService.getSTT(file);

            TextRequestDto sttText = objectMapper.readValue(stt, TextRequestDto.class);
            if (stt.equals("Fail")) {
                throw new Exception("STT 실패");
            }

            // stt로 받은 text의 감정 분석
            String memberEmotion = naverService.getEmotion(EmotionDto.builder()
                    .content(sttText.getText()).build());
            // stt String 값 보호자 -> 이음이 메시지로 저장
            chatHistoryService.saveMemberChat(sttText.getText(), memberEmotion, careNo);

            String chat = chatGPTService.prompt(stt, careNo);

            // chatGPT로 만든 text의 감정 분석
            String ieumEmotion = naverService.getEmotion(EmotionDto.builder().content(chat).build());
            // chat String 값 이음이 -> 보호자 메시지로 저장
            chatHistoryService.saveIeumChat(chat, ieumEmotion, careNo);

            byte[] audioData = googleService.convertTextToSpeech(chat);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "output.mp3");

            // Http response로 mp3 파일 전송
            return new ResponseEntity<>(audioData, headers, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "output.mp3");

            return new ResponseEntity<>(null, headers, HttpStatus.OK);
        }
    }


    private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", data);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> handleFail(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("data", data);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}
