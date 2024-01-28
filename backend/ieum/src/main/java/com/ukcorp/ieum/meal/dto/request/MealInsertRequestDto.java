package com.ukcorp.ieum.meal.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealInsertRequestDto {
    private Long careNo;
    private Long mealTime1;
    private Long mealTime2;
    private Long mealTime3;
}
