package com.ukcorp.ieum.care.controller;

import com.ukcorp.ieum.care.dto.request.CareInsertRequestDto;
import com.ukcorp.ieum.care.dto.request.CareUpdateRequestDto;
import com.ukcorp.ieum.care.dto.response.CareGetResponseDto;
import com.ukcorp.ieum.care.service.CareServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    @PostMapping(value = "/insertImage")
    public ResponseEntity<Map<String,Object>> uploadImage(
            @RequestParam("file") MultipartFile file
    ) {
        try {
            String path = "src/main/resources/static";
            // 파일 저장 디렉토리 경로 (폴더 내에 프론트 프로젝트 함께 존재해야 함)
            String uploadDir = "./profile/";
            String randomId = UUID.randomUUID().toString();
            // 파일 저장할 경로
            String fileName = randomId + ".jpg";
            Path filePath = Paths.get(uploadDir, fileName);
            file.transferTo(filePath); // 파일 다운로드
            return handleSuccess(randomId);

        } catch (Exception e) {
            // 저장 중 에러가 발생한 경우 반환할 응답
            log.debug(e.getMessage());
            return handleError("fail");
        }
    }

    /**
     * 피보호자의 Dto로 Entity에 저장하는 기능
     * @param careDto
     */
    @PostMapping
    private ResponseEntity<Map<String, Object>> insertCareInfo(@RequestBody CareInsertRequestDto careDto) {

        try {
            careService.insertCareInfo(careDto);
            return handleSuccess(careDto);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return handleError("Fail");
        }
    }

    /**
     * 피보호자의 PK로 피보호자 정보를 반환하는 기능
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

    @PutMapping
    private ResponseEntity<Map<String, Object>> updateCareInfo(@RequestBody CareUpdateRequestDto careDto) {

        try {
            careService.updateCareInfo(careDto);
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
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
    }

}
