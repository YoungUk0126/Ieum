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
        MealDto mealDto = mealService.getMeal(careNo);

        if(mealDto != null) {
            return handleSuccess(mealDto);
        }
        else {
            return handleError(mealDto);
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
