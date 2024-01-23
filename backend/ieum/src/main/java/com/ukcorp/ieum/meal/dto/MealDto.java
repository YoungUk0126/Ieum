package com.ukcorp.ieum.meal.dto;


import com.ukcorp.ieum.meal.entity.Meal;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealDto {
    private Long mealInfoNo;
    private Long careNo;
    private Long mealTime1;
    private Long mealTime2;
    private Long mealTime3;

}
