package com.ukcorp.ieum.meal.dto;


import com.ukcorp.ieum.meal.entity.Meal;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MealDto {
    private Long mealTime1;
    private Long mealTime2;
    private Long mealTime3;
    private Long careNo;

    public MealDto(Meal meal) {
        this.mealTime1 = meal.getMealTime1();
        this.mealTime2 = meal.getMealTime2();
        this.mealTime3 = meal.getMealTime3();
        this.careNo = meal.getCareInfo().getCareNo();
    }
}
