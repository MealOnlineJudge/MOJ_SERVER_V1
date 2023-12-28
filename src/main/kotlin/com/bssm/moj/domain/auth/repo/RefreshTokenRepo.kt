package com.bssm.moj.domain.auth.repo

import com.bssm.moj.domain.auth.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepo : JpaRepository<RefreshToken, String>
