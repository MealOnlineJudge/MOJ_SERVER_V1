package com.bssm.moj.global.jwt.util

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import javax.crypto.SecretKey


@ConfigurationProperties(prefix = "auth.jwt")
data class JwtProperties @ConstructorBinding constructor(
    val header: String,
    val secret: SecretKey,
    val accessExp: Long,
    val refreshExp: Long,
    val prefix: String
)
