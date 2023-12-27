package com.bssm.moj.domain.meal.service

import com.bssm.moj.domain.meal.domain.Meal
import com.bssm.moj.domain.meal.domain.repository.MealRepository
import com.bssm.moj.global.exception.ErrorCode
import com.bssm.moj.global.exception.MOJException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
@Transactional(readOnly = true)
class MealReadService(
    private val mealRepository: MealRepository,
) {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")

    fun getMealByDate(date: String?): Meal {
        val date = if (date == null ) LocalDate.now() else LocalDate.parse(date, dateFormatter)
        return mealRepository.findByDate(date).orElseThrow { MOJException(ErrorCode.MEAL_NOT_FOUND) }
    }

}
