package com.ukcorp.ieum.meal.service;

import com.ukcorp.ieum.meal.dto.MealDto;
import com.ukcorp.ieum.meal.entity.Meal;
import com.ukcorp.ieum.meal.mapper.MealMapper;
import com.ukcorp.ieum.meal.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final MealMapper mealMapper;
    @Override
    public MealDto getMeal(Long careNo) throws Exception {
        Optional<Meal> mealTemp = mealRepository.findByCareInfo_CareNo(careNo);

        if(mealTemp.isPresent()) {
            MealDto mealdto = null;

            Meal meal = mealTemp.get();
            mealdto = new MealDto(meal);
            return mealdto;
        } else {
            throw new Exception("존재하지 않는 피보호자입니다.");
        }
    }

    @Transactional
    @Override
    public void insertMeal(MealDto mealDto) {
        Meal meal = mealMapper.mealDtoToMeal(mealDto);
        mealRepository.save(meal);
    }

    @Transactional
    @Override
    public void deleteMeal(Long mealInfoNo) {
        mealRepository.deleteById(mealInfoNo);
    }
}
