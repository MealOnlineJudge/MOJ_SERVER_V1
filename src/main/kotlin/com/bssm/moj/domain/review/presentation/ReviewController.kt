package com.bssm.moj.domain.review.presentation

import com.bssm.moj.domain.review.Review
import com.bssm.moj.domain.review.presentation.dto.CreateReviewDto
import com.bssm.moj.domain.review.service.ReviewService
import com.bssm.moj.global.utils.SecurityUtils
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/review")
class ReviewController(
    private val reviewService: ReviewService,
) {
    @GetMapping
    fun getReviewByMealId(@RequestParam mealId:Long): List<Review> {
        return reviewService.getReviewByMealId(mealId)
    }
    @PostMapping
    fun createReview(@RequestBody createReviewDto: CreateReviewDto): Long {
        return reviewService.createReview(createReviewDto, SecurityUtils.getUser())
    }
    @DeleteMapping("/{id}")
    fun deleteReview(@PathVariable id: Long): Long {
        return reviewService.deleteReview(id)
    }
}
