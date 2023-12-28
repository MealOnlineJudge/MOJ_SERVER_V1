package com.bssm.moj.global.jwt.util

import com.bssm.moj.domain.auth.RefreshToken
import com.bssm.moj.domain.auth.repo.RefreshTokenRepo
import com.bssm.moj.global.jwt.dto.TokenResponseDto
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*

@Component
class JwtProvider(
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepo: RefreshTokenRepo,
) {

    fun generateAccessToken(authId: Long, role: String): String {
        return generateToken(
            authId,
            role,
            "access_token",
            jwtProperties.accessExp
        )
    }

    fun generateToken(authId: Long, role: String): TokenResponseDto {
        val accessToken = generateToken(
            authId,
            role,
            "access_token",
            jwtProperties.accessExp
        )
        val refreshToken = generateToken(
            authId,
            role,
            "refresh_token",
            jwtProperties.refreshExp
        )
        refreshTokenRepo.save(RefreshToken(refreshToken, authId, expiredTime))
        return TokenResponseDto(accessToken, refreshToken, expiredTime)
    }

    private fun generateToken(authId: Long, role: String, type: String, time: Long): String {
        return Jwts.builder()
            .setHeaderParam("type", type)
            .claim("role", role)
            .claim("id", authId)
            .signWith(jwtProperties.secretKey, SignatureAlgorithm.HS256)
            .setExpiration(Date(System.currentTimeMillis() + time * 1000))
            .compact()
    }

    val expiredTime: ZonedDateTime
        get() = ZonedDateTime.now().plusSeconds(jwtProperties.refreshExp)
}
