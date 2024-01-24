package com.ukcorp.ieum.meal.service;

import com.ukcorp.ieum.meal.dto.MealDto;
import com.ukcorp.ieum.meal.dto.request.MealRequestDto;
import com.ukcorp.ieum.meal.dto.response.MealResponseDto;

public interface MealService {
    void insertMeal(MealRequestDto mealRequestDto) throws Exception;
    MealDto getMeal(Long careNo) throws Exception;
    void updateMeal(MealDto mealDto)throws Exception;
    void deleteMeal(Long mealInfoNo);
}
