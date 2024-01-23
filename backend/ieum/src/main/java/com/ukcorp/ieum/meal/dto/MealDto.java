package com.ukcorp.ieum.meal.dto;


import com.ukcorp.ieum.meal.entity.Meal;

public class MealDto {
    private Long mealTIme1;
    private Long mealTIme2;
    private Long mealTIme3;

    public MealDto(Meal meal) {
        this.mealTIme1 = meal.getMealTIme1();
        this.mealTIme2 = meal.getMealTIme2();
        this.mealTIme3 = meal.getMealTIme3();
    }
}
