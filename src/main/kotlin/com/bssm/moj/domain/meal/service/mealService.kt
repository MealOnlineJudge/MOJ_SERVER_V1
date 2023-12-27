package com.bssm.moj.domain.meal.service

import com.bssm.moj.domain.meal.domain.repository.mealRepository
import com.bssm.moj.domain.meal.presentation.dto.request.mealSaveRequestDto
import com.bssm.moj.domain.meal.presentation.dto.request.toEntity
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class mealService(
    private val mealRepository: mealRepository,
) {
    fun create() {
    }

}
