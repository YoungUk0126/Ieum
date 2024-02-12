package com.ukcorp.ieum.care.controller;

import com.ukcorp.ieum.care.dto.request.CareInsertRequestDto;
import com.ukcorp.ieum.care.dto.request.CarePhoneRequestDto;
import com.ukcorp.ieum.care.dto.request.CareUpdateRequestDto;
import com.ukcorp.ieum.care.dto.response.CareGetResponseDto;
import com.ukcorp.ieum.care.service.CareServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/care")
@Slf4j
public class CareController {

  private final CareServiceImpl careService;

  public static void uploadImage(
          String name,
          MultipartFile file
  ) throws IOException {
    // 파일 저장 디렉토리 경로 (폴더 내에 프론트 프로젝트 함께 존재해야 함)
    String uploadDir = "./images/";
    // 파일 저장할 경로
    String fileName = name + ".jpg";
    Path filePath = Paths.get(uploadDir, fileName);
    file.transferTo(filePath); // 파일 다운로드
  }

  @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
  private ResponseEntity<Map<String, Object>> updateCareInfo(
          @RequestPart(value = "data") @Valid CareUpdateRequestDto careDto,
          @RequestPart(value = "file") MultipartFile file
  ) {

    try {
//      파일이 Null값이 아니면 이미지 업로드
      if(file != null) {
        uploadImage(careDto.getCareImage(), file);
      }
      careService.updateCareInfo(careDto);
      return handleSuccess(careDto);

    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleError("Fail");
    }
  }

  /**
   * 피보호자의 PK로 피보호자 정보를 반환하는 기능
   *
   * @param careNo
   */

  @GetMapping
  private ResponseEntity<Map<String, Object>> getCareInfo() {

    try {
      CareGetResponseDto careDto = careService.getCareInfo();
      return handleSuccess(careDto);

    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleError("Fail");
    }
  }


  @DeleteMapping
  private ResponseEntity<Map<String, Object>> deleteCareInfo() {

    try {
      careService.deleteCareInfo();
      return handleSuccess("");

    } catch (Exception e) {
      log.debug(e.getMessage());
      return handleError("Fail");
    }
  }

  @PostMapping("/check-phone")
  private ResponseEntity<Map<String, Object>> checkDuplicatedPhone(@RequestBody CarePhoneRequestDto phoneDto) {
      boolean isExists = careService.isExistsMemberPhone(phoneDto);
      Map<String, Boolean> response = new HashMap<>();
      if (isExists) {
          // 이미 존재하는 핸드폰 번호인 경우
          response.put("isDuplicated", true);
      } else {
          // 사용 가능한 핸드폰 번호인 경우
          response.put("isDuplicated", false);
      }
      return handleSuccess(response);
  }

  private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
    Map<String, Object> result = new HashMap<>();

    result.put("success", true);
    result.put("data", data);
    return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
  }

  private ResponseEntity<Map<String, Object>> handleError(Object data) {
    Map<String, Object> result = new HashMap<>();
    result.put("success", false);
    result.put("data", data);
    return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
  }

}
