package com.bssm.moj.global.jwt.auth

import com.bssm.moj.global.exception.ErrorCode
import com.bssm.moj.global.exception.MOJException
import com.bssm.moj.global.jwt.util.JwtUtil
import com.bssm.moj.global.security.auth.AuthDetailService
import io.jsonwebtoken.Claims
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class JwtAuth(
    val jwtUtil: JwtUtil,
    val authDetailService: AuthDetailService,
) {
    fun authentication(token: String): UsernamePasswordAuthenticationToken {
        val claims: Claims = jwtUtil.getJwt(token).body
        if (isNotAccessToken(token)) {
            throw MOJException(ErrorCode.INVALID_TOKEN)
        }
        val userDetails: UserDetails =
            authDetailService.loadByUsername(claims["id"].toString().toLong())
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun isNotAccessToken(token: String): Boolean {
        if (token.isEmpty()) {
            throw MOJException(ErrorCode.INVALID_TOKEN)
        }
        val role: String = jwtUtil.getJwt(token).header["type"].toString()
        return role != "access_token"
    }
}
