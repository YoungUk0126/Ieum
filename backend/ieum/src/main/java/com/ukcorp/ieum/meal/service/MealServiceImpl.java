package com.ukcorp.ieum.meal.service;

import com.ukcorp.ieum.meal.dto.MealDto;
import com.ukcorp.ieum.meal.entity.Meal;
import com.ukcorp.ieum.meal.mapper.MealMapper;
import com.ukcorp.ieum.meal.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService{

    private final MealRepository mealRepository;
    private final MealMapper mealMapper;
    @Override
    public MealDto getMeal(Long careNo) {
        Optional<Meal> mealTemp = mealRepository.findByCareInfo_CareNo(careNo);
        MealDto mealdto = null;

        if(mealTemp.isPresent()) {
            Meal meal = mealTemp.get();
            mealdto = new MealDto(meal);
        }
        return mealdto;
    }

    @Override
    public void insertMeal(MealDto mealDto) {
        Meal meal = mealMapper.mealDtoToMeal(mealDto);
        mealRepository.save(meal);
    }
}
