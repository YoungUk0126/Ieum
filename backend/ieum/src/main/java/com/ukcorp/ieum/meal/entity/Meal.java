package com.ukcorp.ieum.meal.entity;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.meal.dto.MealDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Table(name = "MEAL_INFO")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEAL_INFO_NO")
    private Long mealInfoNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARE_NO")
    private CareInfo careInfo;

    @Column(name = "MEAL_TIME1")
    private Long mealTime1;

    @Column(name = "MEAL_TIME2")
    private Long mealTime2;

    @Column(name = "MEAL_TIME3")
    private Long mealTime3;

}
