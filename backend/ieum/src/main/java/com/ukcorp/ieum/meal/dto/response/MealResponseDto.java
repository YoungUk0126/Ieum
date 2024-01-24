package com.ukcorp.ieum.meal.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealResponseDto {
    private Long careNo;
    private Long mealTime1;
    private Long mealTime2;
    private Long mealTime3;
}
