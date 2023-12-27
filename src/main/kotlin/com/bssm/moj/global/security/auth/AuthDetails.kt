package com.bssm.moj.global.security.auth

import com.bssm.moj.domain.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class AuthDetails(
    val user: User
):UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.singletonList(
            SimpleGrantedAuthority(user.authority.name)
        )
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
