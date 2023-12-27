package com.bssm.moj.domain.meal.domain.repository

import com.bssm.moj.domain.meal.domain.meal
import org.springframework.data.jpa.repository.JpaRepository

interface mealRepository : JpaRepository<meal,Long>
