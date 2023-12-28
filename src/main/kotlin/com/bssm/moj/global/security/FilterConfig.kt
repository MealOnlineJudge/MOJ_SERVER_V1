package com.bssm.moj.global.security

import com.bssm.moj.global.exception.ExceptionFilter
import com.bssm.moj.global.jwt.auth.JwtAuth
import com.bssm.moj.global.jwt.auth.JwtFilter
import com.bssm.moj.global.jwt.util.JwtUtil
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    val jwtUtil: JwtUtil,
    val jwtAuth: JwtAuth,
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(JwtFilter(jwtAuth, jwtUtil), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(ExceptionFilter(), JwtFilter::class.java)
    }
}
