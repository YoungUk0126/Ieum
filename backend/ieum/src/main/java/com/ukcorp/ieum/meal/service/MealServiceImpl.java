package com.ukcorp.ieum.meal.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.meal.dto.request.MealUpdateRequestDto;
import com.ukcorp.ieum.meal.dto.response.MealGetResponseDto;
import com.ukcorp.ieum.meal.dto.request.MealInsertRequestDto;
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

    private final CareRepository careRepository;

    @Override
    public MealGetResponseDto getMeal(Long careNo) throws Exception {
        Optional<Meal> mealTemp = mealRepository.findByCareInfo_CareNo(careNo);

        if(mealTemp.isPresent()) {
            MealGetResponseDto mealGetResponseDto = null;

            Meal meal = mealTemp.get();
            /**
             * e생성자 였던 부분 mapper로 수정
             * @param meal
             */
            mealGetResponseDto = mealMapper.mealToMealGetResponseDto(meal);
            return mealGetResponseDto;
        } else {
            throw new Exception("존재하지 않는 피보호자입니다.");
        }
    }

    @Transactional
    @Override
    public void insertMeal(MealInsertRequestDto mealInsertRequestDto) throws Exception{
        Optional<CareInfo> careTemp = careRepository.findById(mealInsertRequestDto.getCareNo());
        if(careTemp.isPresent()){
            CareInfo care = careTemp.get();
            Meal meal = mealMapper.mealInsertRequestDtoAndCareInfoToMeal(mealInsertRequestDto, care);
            mealRepository.save(meal);
        } else {
            throw new Exception("존재하지 않는 피보호자입니다.");

        }

    }


    @Transactional
    @Override
    public void updateMeal(MealUpdateRequestDto mealUpdateRequestDto) throws Exception {

//        PK에 해당하는 데이터가 있는지 조회
        Optional<Meal> mealTemp = mealRepository.findById(mealUpdateRequestDto.getMealInfoNo());

        if(mealTemp.isPresent()) {
            Meal meal = mealMapper.mealUpdateRequestDtoToMeal(mealUpdateRequestDto);
//            save로 자동 merge(update)
            mealRepository.save(meal);
        } else {
            throw new Exception("수정할 식사 시간 정보가 없습니다.");
        }
    }

    @Transactional
    @Override
    public void deleteMeal(Long mealInfoNo) {
        mealRepository.deleteById(mealInfoNo);
    }


}
