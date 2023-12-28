package com.bssm.moj.global.jwt.util

import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import javax.crypto.SecretKey

@ConfigurationProperties(prefix = "auth.jwt")
class JwtProperties @ConstructorBinding constructor(
    val header: String,
    secret: String,
    val accessExp: Long,
    val refreshExp: Long,
    val prefix: String,
) {
    val secretKey: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))
}
