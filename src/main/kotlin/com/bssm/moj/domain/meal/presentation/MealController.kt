package com.bssm.moj.domain.meal.presentation

import com.bssm.moj.domain.meal.service.mealService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class mealController(
    private val mealService: mealService,
) {
    @GetMapping("/meal/save")
    fun saveMeal() {
        mealService.create()
    }

}
