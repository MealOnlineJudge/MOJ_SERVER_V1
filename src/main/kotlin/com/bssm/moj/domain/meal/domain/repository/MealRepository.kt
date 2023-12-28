package com.bssm.moj.domain.meal.domain.repository

import com.bssm.moj.domain.meal.domain.Meal
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.Optional

interface MealRepository : JpaRepository<Meal, Long> {
    fun findByDate(date: LocalDate): Optional<Meal>
}
