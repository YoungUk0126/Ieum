package com.ukcorp.ieum.meal.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.meal.dto.request.MealInsertRequestDto;
import com.ukcorp.ieum.meal.dto.request.MealUpdateRequestDto;
import com.ukcorp.ieum.meal.dto.response.MealGetResponseDto;
import com.ukcorp.ieum.meal.entity.Meal;
import com.ukcorp.ieum.meal.mapper.MealMapper;
import com.ukcorp.ieum.meal.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

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
        try {
//            피보호자 PK로 끼니시간 정보 데이터를 받아옴, 없다면 Exception
            Meal meal = mealRepository.findByCareInfo_CareNo(careNo).orElseThrow(() -> new NoSuchElementException("존재하지 않는 끼니 시간 정보입니다."));
            return mealMapper.mealToMealGetResponseDto(meal);

        } catch (RuntimeException e) {
            log.debug("조회 오류!");
            throw new Exception("조회 오류!");
        }
    }

    @Transactional
    @Override
    public void insertMeal(MealInsertRequestDto mealInsertRequestDto) throws Exception {
        try {
//            피보호자 정보가 있는지 확인 및 받아오기
            CareInfo care = careRepository.findById(mealInsertRequestDto.getCareNo()).orElseThrow(() -> new NoSuchElementException("존재하지 않는 피보호자 정보입니다."));
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
//            피보호자 정보가 있는지 확인
            CareInfo care = careRepository.findById(mealUpdateRequestDto.getCareNo()).orElseThrow(() -> new NoSuchElementException("존재하지 않는 피보호자 정보입니다."));
//            수정할 끼니시간 정보가 있는지 확인
            Meal check = mealRepository.findById(mealUpdateRequestDto.getMealInfoNo()).orElseThrow(() -> new NoSuchElementException("존재하지 않는 끼니시간 정보입니다."));
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
