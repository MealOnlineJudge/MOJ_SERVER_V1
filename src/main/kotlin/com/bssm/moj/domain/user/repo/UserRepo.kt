package com.bssm.moj.domain.user.repo

import com.bssm.moj.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepo : JpaRepository<User, Long>
