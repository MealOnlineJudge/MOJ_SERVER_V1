package com.bssm.moj.domain.ranking.presentation

import com.bssm.moj.domain.ranking.presentation.dto.RankingResponse
import com.bssm.moj.domain.ranking.service.RankingService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ranking")
class RankingController(
    private val rankingService: RankingService,
) {
    @RequestMapping("/meal")
    fun getMealRanking(): List<RankingResponse> {
        return rankingService.getMealRanking()
    }
}
