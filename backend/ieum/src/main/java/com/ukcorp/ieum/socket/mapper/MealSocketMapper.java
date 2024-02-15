package com.ukcorp.ieum.socket.mapper;

import com.ukcorp.ieum.meal.entity.Meal;
import com.ukcorp.ieum.socket.dto.response.MealResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MealSocketMapper {

    MealResponseDto mealToMealResponseDto(Meal meal);
}
