package com.bssm.moj.domain.meal.service.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "neis")
data class MealProperties @ConstructorBinding constructor(
    val key: String,
    val mealUrl: String,
)
