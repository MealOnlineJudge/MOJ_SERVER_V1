package com.bssm.moj.domain.auth.service

import com.bssm.moj.domain.user.User
import com.bssm.moj.domain.user.repo.UserRepo
import com.bssm.moj.global.exception.ErrorCode
import com.bssm.moj.global.exception.MOJException
import leehj050211.bsmOauth.BsmOauth
import leehj050211.bsmOauth.dto.resource.BsmUserResource
import leehj050211.bsmOauth.exception.BsmOAuthCodeNotFoundException
import leehj050211.bsmOauth.exception.BsmOAuthInvalidClientException
import leehj050211.bsmOauth.exception.BsmOAuthTokenNotFoundException
import team.untitled.unboxingBackend.domain.user.authority.Authority
import java.io.IOException
import java.util.*


class UserSaveService(
    private val bsmOauth: BsmOauth,
    private val userRepo: UserRepo
) {


    @Throws(IOException::class)
    fun execute(authId: String?): User {
        val token: String
        val resource: BsmUserResource
        try {
            token = bsmOauth.getToken(authId)
            resource = bsmOauth.getResource(token)
        } catch (e: BsmOAuthCodeNotFoundException) {
            throw MOJException(ErrorCode.USER_NOT_LOGIN)
        } catch (e: BsmOAuthTokenNotFoundException) {
            throw MOJException(ErrorCode.USER_NOT_LOGIN)
        } catch (e: BsmOAuthInvalidClientException) {
            throw MOJException(ErrorCode.BSM_AUTH_INVALID_CLIENT)
        }
        return saveUser(resource)
    }

    private fun saveUser(resource: BsmUserResource): User {
        val user = User(
            resource.userCode,
            resource.student.name,
            resource.email,
            resource.profileUrl,
            Authority.USER,
            resource.nickname,
            resource.student.enrolledAt,
            resource.student.grade,
            resource.student.classNo,
            resource.student.studentNo,
        )

        return userRepo.save(user)
    }
}

