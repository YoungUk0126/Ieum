package com.ukcorp.ieum.meal.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.meal.dto.MealDto;
import com.ukcorp.ieum.meal.dto.request.MealRequestDto;
import com.ukcorp.ieum.meal.dto.response.MealResponseDto;
import com.ukcorp.ieum.meal.entity.Meal;
import com.ukcorp.ieum.meal.mapper.MealMapper;
import com.ukcorp.ieum.meal.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final CareRepository careRepository;
    private final MealMapper mealMapper;
    @Override
    public MealResponseDto getMeal(Long careNo) throws Exception {
        try {

        } catch (RuntimeException e) {

        }
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
    public void insertMeal(MealRequestDto mealRequestDto) throws Exception{
        try {
            CareInfo care = careRepository.findById(mealRequestDto.getCareNo()).orElse(null);
            if(care == null) {
                throw new Exception("피보호자 정보 오류");
            }
            Meal meal = mealRepository.findById(mealRequestDto.getMealInfoNo()).orElse(null);
            if(meal == null) {
                throw new Exception("끼니 시간 정보 오류");
            }

        } catch (DataIntegrityViolationException e) {
            log.debug("수정 오류!");
            throw new Exception("식사 시간 정보가 없습니다.");

        }
    }


    @Transactional
    @Override
    public void updateMeal(MealDto mealDto) throws Exception {
        try {
            CareInfo care = careRepository.findById(mealDto.getCareNo()).orElse(null);
            if(care == null) {
                throw new Exception("피보호자 정보 오류");
            }
            Meal check = mealRepository.findById(mealDto.getMealInfoNo()).orElse(null);
            if(check == null) {
                throw new Exception("끼니 시간 정보 오류");
            }
            Meal meal = mealMapper.mealDtoToMeal(mealDto);

        } catch (DataIntegrityViolationException e) {
            log.debug("수정 오류!");
            throw new Exception("식사 시간 정보가 없습니다.");

        }
    }

    @Transactional
    @Override
    public void deleteMeal(Long mealInfoNo) throws Exception {
        try {
            mealRepository.deleteById(mealInfoNo);
        } catch (EmptyResultDataAccessException e) {
            log.debug("삭제 오류!");
            throw new Exception("삭제 오류!");
        }
    }


}
