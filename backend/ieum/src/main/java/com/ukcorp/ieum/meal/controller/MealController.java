package com.ukcorp.ieum.meal.controller;

import com.ukcorp.ieum.meal.dto.MealDto;
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

    @GetMapping("/{care-no}")
    public ResponseEntity<Map<String, Object>> getMeal(@PathVariable("care-no") Long careNo) {

        try{
            MealDto mealDto = mealService.getMeal(careNo);
            return handleSuccess(mealDto);
        } catch (Exception e){
            log.debug(e.getMessage());
            return handleError("Fail");
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> insertMeal(@RequestBody MealDto mealDto) {
        try {
            mealService.insertMeal(mealDto);
            return handleSuccess("");
        } catch (Exception e) {
            return handleError("Fail");
        }

    }

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
