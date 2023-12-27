package com.bssm.moj.domain.auth.repo

import org.springframework.data.jpa.repository.JpaRepository
import com.bssm.moj.domain.auth.RefreshToken

interface RefreshTokenRepo:JpaRepository<RefreshToken,String> {
}
