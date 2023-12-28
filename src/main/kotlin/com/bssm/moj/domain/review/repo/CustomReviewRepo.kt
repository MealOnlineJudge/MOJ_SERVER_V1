package com.bssm.moj.domain.review.repo

import com.bssm.moj.domain.meal.domain.QMeal.meal
import com.bssm.moj.domain.ranking.presentation.dto.RankingResponse
import com.bssm.moj.domain.review.QReview.review
import com.bssm.moj.domain.review.Review
import com.querydsl.core.group.GroupBy.avg
import com.querydsl.core.types.ExpressionUtils.count
import com.querydsl.core.types.Projections
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.expression.spel.ast.Projection
import java.time.LocalDateTime
import java.time.YearMonth

interface CustomReviewRepo {
    fun findRanking(): List<RankingResponse>
}

class CustomReviewRepoImpl : CustomReviewRepo, QuerydslRepositorySupport(Review::class.java) {
    override fun findRanking(): List<RankingResponse> {
        val review = from(meal)
            .join( review.meal ,meal)
            .select(Projections.constructor(RankingResponse::class.java, avg(review.rating),count(review), meal))
            .where(review.createDateTime.between(LocalDateTime.now().minusDays(7), LocalDateTime.now()))
            .orderBy(review.rating.desc(),review.createDateTime.asc())
            .fetch()

        return review
    }
}
