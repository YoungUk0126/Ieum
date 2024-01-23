package com.ukcorp.ieum.meal.mapper;

import com.ukcorp.ieum.meal.dto.MealDto;
import com.ukcorp.ieum.meal.entity.Meal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MealMapper {
    @Mapping(target = "mealTime1", source = "mealTime1")
    @Mapping(target = "mealTime1", source = "mealTime2")
    @Mapping(target = "mealTime1", source = "mealTime3")
    @Mapping(target = "mealInfoNo", ignore = true)
    @Mapping(target = "careInfo", ignore = true)
    Meal mealDtoToMeal(MealDto meal);
}
