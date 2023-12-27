package com.bssm.moj.domain.review.service

import com.bssm.moj.domain.meal.domain.Meal
import com.bssm.moj.domain.meal.domain.repository.MealRepository
import com.bssm.moj.domain.review.Review
import com.bssm.moj.domain.review.presentation.dto.CreateReviewDto
import com.bssm.moj.domain.review.repo.ReviewRepo
import com.bssm.moj.domain.user.User
import com.bssm.moj.global.exception.ErrorCode
import com.bssm.moj.global.exception.MOJException
import jakarta.persistence.Entity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ReviewService(
    val reviewRepo: ReviewRepo,
    val mealRepo: MealRepository,
) {
    fun getReviewByMealId(mealId: Long): List<Review> {
        val meal:Meal = mealRepo.findById(mealId).orElseThrow{ MOJException(ErrorCode.MEAL_NOT_FOUND)}
        return reviewRepo.findByMeal(meal);
    }
    fun createReview(reviewDto: CreateReviewDto, user: User): Long {
        val meal = mealRepo.findById(reviewDto.mealId).orElseThrow{ MOJException(ErrorCode.MEAL_NOT_FOUND)}
        val review = Review(
            meal = meal,
            content = reviewDto.content,
            rating = reviewDto.rating,
            user = user
        )
        return reviewRepo.save(review).id
    }
    fun deleteReview(reviewId: Long): Long {
        reviewRepo.findById(reviewId).orElseThrow{ MOJException(ErrorCode.REVIEW_NOT_FOUND)}
        reviewRepo.deleteById(reviewId)
        return reviewId
    }
}
