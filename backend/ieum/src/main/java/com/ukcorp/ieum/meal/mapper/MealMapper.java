package com.ukcorp.ieum.meal.mapper;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.meal.dto.request.MealUpdateRequestDto;
import com.ukcorp.ieum.meal.dto.response.MealGetResponseDto;
import com.ukcorp.ieum.meal.dto.request.MealInsertRequestDto;
import com.ukcorp.ieum.meal.entity.Meal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MealMapper {
    @Mapping(target = "mealInfoNo", ignore = true)
    @Mapping(target = "meal.careNo", ignore = true)
    @Mapping( source = "care", target = "careInfo")
    Meal mealInsertRequestDtoAndCareInfoToMeal(MealInsertRequestDto meal, CareInfo care);


    @Mapping(target = "meal.careNo", ignore = true)
    @Mapping( source = "care", target = "careInfo")
    Meal mealUpdateRequestDtoAndCareInfoToMeal(MealUpdateRequestDto meal, CareInfo care);
//    Meal안에 있는 careInfo라는 객체 안에 careNo를 mealGetResponseDto의 careNo에 매핑하겠다.
    @Mapping(source = "meal.careInfo.careNo", target= "careNo")
    MealGetResponseDto mealToMealGetResponseDto(Meal meal);

}
