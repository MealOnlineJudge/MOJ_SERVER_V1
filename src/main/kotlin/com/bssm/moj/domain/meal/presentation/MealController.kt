package com.bssm.moj.domain.meal.presentation

import com.bssm.moj.domain.meal.domain.Meal
import com.bssm.moj.domain.meal.service.MealReadService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/meal")
class MealController(
    private val mealReadService: MealReadService,
) {
    @GetMapping
    fun saveMeal(@RequestParam date: String?): Meal {
        return mealReadService.getMealByDate(date)
    }
}
