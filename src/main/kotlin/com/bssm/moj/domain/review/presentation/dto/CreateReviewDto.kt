package com.bssm.moj.domain.review.presentation.dto

data class CreateReviewDto(
    val mealId: Long,
    val content: String,
    val rating: Double,
)
