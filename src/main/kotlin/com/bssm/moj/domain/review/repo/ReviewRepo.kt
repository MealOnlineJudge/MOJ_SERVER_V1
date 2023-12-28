package com.bssm.moj.domain.review.repo

import com.bssm.moj.domain.meal.domain.Meal
import com.bssm.moj.domain.review.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepo : JpaRepository<Review, Long>, CustomReviewRepo {

    fun findByMeal(meal: Meal): List<Review>
}
