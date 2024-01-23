package com.ukcorp.ieum.meal.repository;

import com.ukcorp.ieum.meal.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
