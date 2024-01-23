package com.ukcorp.ieum.meal.service;

import com.ukcorp.ieum.meal.dto.MealDto;

public interface MealService {
    MealDto getMeal(Long careNo) throws Exception;

    void insertMeal(MealDto mealDto);

    void deleteMeal(Long mealInfoNo);
}
