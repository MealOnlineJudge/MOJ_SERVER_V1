package com.bssm.moj.global.jwt.util

import com.bssm.moj.domain.auth.repo.RefreshTokenRepo
import com.bssm.moj.global.exception.ErrorCode
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import com.bssm.moj.global.exception.MOJException
import jakarta.servlet.http.HttpServletRequest


@Component
class JwtUtil(
    val jwtProperties: JwtProperties,
    val refreshTokenRepo: RefreshTokenRepo
) {

    fun resolveToken(request: HttpServletRequest): String? {
        val bearer:String? = request.getHeader(jwtProperties.header)
        return parseToken(bearer)
    }
    fun parseToken(bToken:String?):String? {
        if(!bToken.isNullOrBlank()){
           return bToken.replace("Bearer","").trim()
        }
        return null
    }
    fun getJwt(token: String): Jws<Claims> {
        return Jwts.parserBuilder().setSigningKey(jwtProperties.secret).build().parseClaimsJws(token)
    }

    fun getJws(token: String?): Jws<Claims> {
        return try {
            Jwts.parserBuilder().setSigningKey(jwtProperties.secret).build()
                .parseClaimsJws(token)
        } catch (e: ExpiredJwtException) {
            throw MOJException(ErrorCode.EXPIRED_JWT)
        } catch (e: Exception) {
            throw  MOJException(ErrorCode.INTERNAL_SERVER_ERROR)
        }
    }

}
