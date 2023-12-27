package com.bssm.moj.domain.meal.service.properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.stereotype.Component


@ConfigurationProperties(prefix = "neis")
data class MealProperties @ConstructorBinding constructor(
    val key: String,
    val mealUrl: String,
) {
}

