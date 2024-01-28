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
    public MealGetResponseDto getMeal(Long careNo) throws Exception {
        Optional<Meal> mealTemp = mealRepository.findByCareInfo_CareNo(careNo);

        if (mealTemp.isPresent()) {
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
    public void insertMeal(MealInsertRequestDto mealInsertRequestDto) throws Exception {
        try {
            CareInfo care = careRepository.findById(mealInsertRequestDto.getCareNo()).orElse(null);
            if (care == null) {
                throw new Exception("피보호자 정보 오류");
            }
            Meal meal = mealMapper.mealInsertRequestDtoAndCareInfoToMeal(mealInsertRequestDto, care);
            mealRepository.save(meal);
        } catch (DataIntegrityViolationException e) {
            log.debug("등록 오류!");
            throw new Exception("식사 시간 정보가 없습니다.");
        }
    }


    @Transactional
    @Override
    public void updateMeal(MealUpdateRequestDto mealUpdateRequestDto) throws Exception {

        try {
            CareInfo care = careRepository.findById(mealUpdateRequestDto.getCareNo()).orElse(null);
            if (care == null) {
                throw new Exception("피보호자 정보 오류");
            }
            Meal check = mealRepository.findById(mealUpdateRequestDto.getMealInfoNo()).orElse(null);
            if (check == null) {
                throw new Exception("끼니 시간 정보 오류");
            }
            Meal meal = mealMapper.mealUpdateRequestDtoToMeal(mealUpdateRequestDto);
//            save로 자동 merge(update)
            mealRepository.save(meal);

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
