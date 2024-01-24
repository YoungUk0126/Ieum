package com.ukcorp.ieum.meal.service;

import com.ukcorp.ieum.meal.dto.MealDto;
import com.ukcorp.ieum.meal.dto.request.MealRequestDto;
import com.ukcorp.ieum.meal.dto.response.MealResponseDto;

public interface MealService {
    MealDto getMeal(Long careNo) throws Exception;

    void insertMeal(MealRequestDto mealRequestDto);

    void deleteMeal(Long mealInfoNo);

    void updateMeal(MealDto mealDto)throws Exception;
}
