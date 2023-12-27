package com.bssm.moj.domain.auth

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.ZonedDateTime

@Entity
class RefreshToken(
    @Id
    val refreshToken: String,

    val userId: Long,

    val expiredAt: ZonedDateTime
)
