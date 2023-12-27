package com.bssm.moj.global.utils

import org.springframework.security.core.context.SecurityContextHolder
import com.bssm.moj.domain.user.User
import com.bssm.moj.global.exception.ErrorCode
import com.bssm.moj.global.exception.MOJException
import com.bssm.moj.global.security.auth.AuthDetails

class SecurityUtils {
    companion object {
        fun getCurrentUserOrNull(): User? {
            return try {
                getUser()
            } catch (e: Exception) {
                null
            }
        }

        fun getUser(): User {
            val principal = SecurityContextHolder.getContext()
                .authentication
                .principal
            if (principal is String) {
                throw MOJException(ErrorCode.FORBIDDEN)
            }
            val authDetails: AuthDetails = principal as AuthDetails
            return authDetails.user
        }
    }
}
