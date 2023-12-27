package com.bssm.moj.global.security.auth

import com.bssm.moj.domain.user.User
import com.bssm.moj.domain.user.repo.UserRepo
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthDetailService(
    val userRepo: UserRepo
) {
    fun loadByUsername(id: Long):UserDetails{
        val user: User =  userRepo.findById(id).orElseThrow();
        return AuthDetails(user)
    }
}
