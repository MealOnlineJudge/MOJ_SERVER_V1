package com.bssm.moj.domain.ranking.presentation.dto

import com.bssm.moj.domain.meal.domain.Meal
import com.querydsl.core.annotations.QueryProjection

data class RankingResponse(
    val avgRating: Double,
    val countRating: Int,
    val meal:Meal
) {
}
