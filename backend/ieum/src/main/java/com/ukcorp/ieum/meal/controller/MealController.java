package com.ukcorp.ieum.meal.controller;

import com.ukcorp.ieum.meal.dto.request.MealUpdateRequestDto;
import com.ukcorp.ieum.meal.dto.response.MealGetResponseDto;
import com.ukcorp.ieum.meal.dto.request.MealInsertRequestDto;
import com.ukcorp.ieum.meal.service.MealServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/meal")
@AllArgsConstructor
@Slf4j
public class MealController {

    private final MealServiceImpl mealService;

    /**
     * 피보호자의 FK로 피보호자 끼니 정보를 반환하는 기능
     * @param careNo
     */
    @GetMapping("/{care-no}")
    public ResponseEntity<Map<String, Object>> getMeal(@PathVariable("care-no") Long careNo) {

        try{
            MealGetResponseDto mealGetResponseDto = mealService.getMeal(careNo);
            return handleSuccess(mealGetResponseDto);
        } catch (Exception e){
            log.debug(e.getMessage());
            return handleError("Fail");
        }
    }

    /**
     * PK를 제외한 데이터를 받아 Entity로 변환하여 DB에 데이터를 넣는 기능
     * @param mealInsertRequestDto
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> insertMeal(@RequestBody MealInsertRequestDto mealInsertRequestDto) {
        try {
            mealService.insertMeal(mealInsertRequestDto);
            return handleSuccess(mealInsertRequestDto);
        } catch (Exception e) {
            return handleError("Fail");
        }

    }

    /**
     * 변경된 meal을 가져와 PK로 해당 데이터가 있는지 확인 후 있으면 덮어씌워주는 기능
     * @param mealUpdateRequestDto
     */
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateMeal(@RequestBody MealUpdateRequestDto mealUpdateRequestDto) {
        try {
            mealService.updateMeal(mealUpdateRequestDto);
            return handleSuccess("");
        } catch (Exception e) {
            return handleError("Fail");
        }
    }

    /**
     * 해당 끼니 정보를 삭제하는 기능
     * @param mealInfoNo
     */
    @DeleteMapping("/{meal-info-no}")
    public ResponseEntity<Map<String, Object>> deleteMeal(@PathVariable("meal-info-no") Long mealInfoNo) {
        try{
            mealService.deleteMeal(mealInfoNo);
            return handleSuccess("");
        } catch (Exception e) {
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
