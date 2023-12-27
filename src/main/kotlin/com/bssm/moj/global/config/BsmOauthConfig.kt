package com.bssm.moj.global.config

import leehj050211.bsmOauth.BsmOauth
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.context.annotation.Bean

@ConfigurationProperties(prefix = "bsm")
class BsmOauthConfig @ConstructorBinding constructor(
    private val client_id: String,
    private val client_secret: String
) {
    @Bean("bsmOauth")
    fun bsmOauth(): BsmOauth {
        return BsmOauth(client_id, client_secret)
    }
}

