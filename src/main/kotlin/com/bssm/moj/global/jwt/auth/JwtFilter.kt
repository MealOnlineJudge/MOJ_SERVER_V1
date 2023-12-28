package com.bssm.moj.global.jwt.auth

import com.bssm.moj.global.jwt.util.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtFilter(val jwtAuth: JwtAuth, val jwtUtil: JwtUtil) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token: String? = jwtUtil.resolveToken(request)
        SetAuthenticationInSecurityContext(token)
        filterChain.doFilter(request, response)
    }

    private fun SetAuthenticationInSecurityContext(token: String?) {
        if (token != null) {
            val authentication = jwtAuth.authentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
    }
}
