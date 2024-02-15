package com.ukcorp.ieum.meal.service;

import com.ukcorp.ieum.meal.dto.request.MealUpdateRequestDto;
import com.ukcorp.ieum.meal.dto.response.MealGetResponseDto;
import com.ukcorp.ieum.meal.dto.request.MealInsertRequestDto;

public interface MealService {

    void insertMeal(MealInsertRequestDto mealInsertRequestDto) throws Exception;
    MealGetResponseDto getMeal() throws Exception;
    void updateMeal(MealUpdateRequestDto mealUpdateRequestDto)throws Exception;
    void deleteMeal(Long mealInfoNo) throws Exception;
}
