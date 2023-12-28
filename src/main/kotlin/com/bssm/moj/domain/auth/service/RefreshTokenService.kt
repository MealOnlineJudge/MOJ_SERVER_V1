package com.bssm.moj.domain.auth.service

import com.bssm.moj.domain.auth.repo.RefreshTokenRepo
import com.bssm.moj.domain.user.User
import com.bssm.moj.domain.user.UserFacade
import com.bssm.moj.global.exception.ErrorCode
import com.bssm.moj.global.exception.MOJException
import com.bssm.moj.global.jwt.dto.TokenResponseDto
import com.bssm.moj.global.jwt.util.JwtProvider
import com.bssm.moj.global.jwt.util.JwtUtil
import org.springframework.stereotype.Service

@Service
class RefreshTokenService(
    private val jwtUtil: JwtUtil,
    private val jwtProvider: JwtProvider,
    private val refreshTokenRepo: RefreshTokenRepo,
    private val userFacade: UserFacade,
) {
    fun execute(token: String): TokenResponseDto {
        val refreshToken = refreshTokenRepo.findById(token)
            .orElseThrow { MOJException(ErrorCode.FORBIDDEN) }

        jwtUtil.getJws(jwtUtil.parseToken(token))

        val user: User = userFacade.getUserById(refreshToken.userId)

        return TokenResponseDto(
            jwtProvider.generateAccessToken(refreshToken.userId, user.authority.toString()),
            refreshToken.refreshToken,
            refreshToken.expiredAt
        )
    }
}
