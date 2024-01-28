package com.ukcorp.ieum.meal.service;

import com.ukcorp.ieum.meal.dto.MealDto;
import com.ukcorp.ieum.meal.dto.request.MealRequestDto;
import com.ukcorp.ieum.meal.dto.response.MealResponseDto;

public interface MealService {
    MealResponseDto getMeal(Long careNo) throws Exception;

    void insertMeal(MealRequestDto mealRequestDto) throws Exception;

    void deleteMeal(Long mealInfoNo) throws Exception;

    void updateMeal(MealDto mealDto)throws Exception;
}
