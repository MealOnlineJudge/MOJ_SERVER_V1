package com.bssm.moj.domain.user.presentation

import com.bssm.moj.domain.user.User
import com.bssm.moj.domain.user.presentation.dto.UserRes
import com.bssm.moj.global.utils.SecurityUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {
    @GetMapping("me")
    fun findMyInfo(): UserRes {
        val user: User = SecurityUtils.getUser()
        return UserRes(user.id, user.name, user.email, user.profileImage)
    }
}
