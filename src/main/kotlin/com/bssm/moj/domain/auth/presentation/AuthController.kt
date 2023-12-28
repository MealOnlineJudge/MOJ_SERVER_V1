package com.bssm.moj.domain.auth.presentation

import com.bssm.moj.domain.auth.service.BsmLoginService
import com.bssm.moj.domain.auth.service.RefreshTokenService
import com.bssm.moj.global.exception.ErrorCode
import com.bssm.moj.global.exception.MOJException
import com.bssm.moj.global.jwt.dto.TokenResponseDto
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val refreshTokenService: RefreshTokenService,
    private val bsmLoginService: BsmLoginService,
) {

    @PostMapping("/oauth/bsm")
    fun userSignup(@RequestBody userLoginReq: @Valid UserLoginReq): TokenResponseDto {
        return bsmLoginService.execute(userLoginReq.authCode)
    }

    @PutMapping("refreshToken")
    fun refreshToken(@RequestBody refreshToken: @NotBlank String?): TokenResponseDto {
        if (refreshToken == null) throw MOJException(ErrorCode.INVALID_ARGUMENT)
        return refreshTokenService.execute(refreshToken)
    }
}
