package com.bssm.moj.domain.meal.presentation.dto.request

import com.bssm.moj.domain.meal.domain.Meal
import com.bssm.moj.domain.meal.domain.type.daily
import java.time.LocalDate

class mealSaveRequestDto(
    val date: LocalDate,
    val dailyType: daily
) fun mealSaveRequestDto.toEntity(): Meal {
    return Meal.saveMeal(
        date = date,
        dailyType = dailyType
    )
}
