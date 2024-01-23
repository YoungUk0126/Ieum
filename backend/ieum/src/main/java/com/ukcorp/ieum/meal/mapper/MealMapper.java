package com.ukcorp.ieum.meal.mapper;

import com.ukcorp.ieum.meal.dto.MealDto;
import com.ukcorp.ieum.meal.entity.Meal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MealMapper {
    @Mapping(target = "mealInfoNo", ignore = true)
    @Mapping(target = "careInfo", ignore = true)
    Meal mealDtoToMeal(MealDto meal);

    @Mapping(source = "careInfo.careNo", target="careNo")
    MealDto mealToMealDto(Meal meal);
}
