package com.bssm.moj.domain.user.presentation.dto

data class UserRes(
    val id: Long? = null,

    var name: String,

    val email: String,

    var profileImage: String,
)
