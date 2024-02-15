package com.ukcorp.ieum.meal.repository;

import com.ukcorp.ieum.meal.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Optional<Meal> findByCareInfo_CareNo(Long CareNo);
}
