package com.bssm.moj.domain.ranking.service

import com.bssm.moj.domain.ranking.presentation.dto.RankingResponse
import com.bssm.moj.domain.review.Review
import com.bssm.moj.domain.review.repo.ReviewRepo
import org.springframework.stereotype.Service

@Service
class RankingService(
    private val reviewRepo: ReviewRepo
) {
    fun getMealRanking(): List<RankingResponse> {
        return reviewRepo.findRanking()
    }
}
