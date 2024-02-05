package com.ukcorp.ieum.socket.dto.response;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealResponseDto implements Response{
    private Long mealTime1;
    private Long mealTime2;
    private Long mealTime3;
}
