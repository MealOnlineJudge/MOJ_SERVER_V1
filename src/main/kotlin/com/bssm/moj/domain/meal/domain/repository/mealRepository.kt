package com.bssm.moj.domain.meal.domain.repository

import com.bssm.moj.domain.meal.domain.Meal
import org.springframework.data.jpa.repository.JpaRepository

interface mealRepository : JpaRepository<Meal,Long>
