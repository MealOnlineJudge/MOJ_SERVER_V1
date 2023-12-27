package com.bssm.moj.domain.user.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.bssm.moj.domain.user.User
import java.util.*

interface UserRepo: JpaRepository<User,Long> {
}
