package com.ukcorp.ieum.meal.service;

import com.ukcorp.ieum.meal.dto.MealDto;
import com.ukcorp.ieum.meal.dto.request.MealRequestDto;
import com.ukcorp.ieum.meal.dto.response.MealResponseDto;
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
    public MealResponseDto getMeal(Long careNo) throws Exception {
        Optional<Meal> mealTemp = mealRepository.findByCareInfo_CareNo(careNo);

        if(mealTemp.isPresent()) {
            MealResponseDto mealResponseDto = null;

            Meal meal = mealTemp.get();
            /**
             * e생성자 였던 부분 mapper로 수정
             * @param meal
             */
            mealResponseDto = mealMapper.mealToMealResponseDto(meal);
            return mealResponseDto;
        } else {
            throw new Exception("존재하지 않는 피보호자입니다.");
        }
    }

    @Transactional
    @Override
    public void insertMeal(MealRequestDto mealRequestDto) {
        Meal meal = mealMapper.mealRequestDtoToMeal(mealRequestDto);
        mealRepository.save(meal);
    }


    @Transactional
    @Override
    public void updateMeal(MealDto mealDto) throws Exception {

//        PK에 해당하는 데이터가 있는지 조회
        Optional<Meal> mealTemp = mealRepository.findById(mealDto.getMealInfoNo());

        if(mealTemp.isPresent()) {
            Meal meal = mealMapper.mealDtoToMeal(mealDto);
//            save로 자동 merge(update)
            mealRepository.save(meal);
        } else {
            throw new Exception("식사 시간 정보가 없습니다.");
        }
    }

    @Transactional
    @Override
    public void deleteMeal(Long mealInfoNo) {
        mealRepository.deleteById(mealInfoNo);
    }


}
