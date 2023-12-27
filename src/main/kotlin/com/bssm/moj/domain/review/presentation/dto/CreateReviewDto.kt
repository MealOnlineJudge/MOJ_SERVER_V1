package com.bssm.moj.domain.review.presentation.dto

import com.bssm.moj.domain.review.Review

data class CreateReviewDto(
    val mealId: Long,
    val content: String,
    val rating: Double,
){}
