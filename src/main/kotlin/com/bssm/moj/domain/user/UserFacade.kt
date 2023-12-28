package com.bssm.moj.domain.user

import com.bssm.moj.domain.user.repo.UserRepo
import com.bssm.moj.global.exception.ErrorCode
import com.bssm.moj.global.exception.MOJException
import org.springframework.stereotype.Component

@Component
class UserFacade(
    val userRepo: UserRepo,
) {
    fun getUserById(id: Long): User {
        return userRepo.findById(id)
            .orElseThrow { MOJException(ErrorCode.USER_NOT_FOUND) }
    }
}
