package com.bssm.moj.domain.auth.service

import com.bssm.moj.domain.auth.RefreshToken
import com.bssm.moj.domain.auth.repo.RefreshTokenRepo
import com.bssm.moj.domain.user.User
import com.bssm.moj.global.jwt.dto.TokenResponseDto
import com.bssm.moj.global.jwt.util.JwtProperties
import com.bssm.moj.global.jwt.util.JwtProvider
import java.io.IOException

class BsmLoginService(
    private val userSaveService: UserSaveService,
    private val jwtProvider: JwtProvider,
) {

    @Throws(IOException::class)
    fun execute(authId: String): TokenResponseDto {
        val user: User = userSaveService.execute(authId)

        return jwtProvider.generateToken(user.id, user.authority.name)
    }

}
