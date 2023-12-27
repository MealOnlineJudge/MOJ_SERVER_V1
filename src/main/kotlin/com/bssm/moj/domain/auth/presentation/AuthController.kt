package com.bssm.moj.domain.auth.presentation

import org.springframework.web.bind.annotation.*
import com.bssm.moj.domain.auth.service.RefreshTokenService
import com.bssm.moj.global.exception.ErrorCode
import com.bssm.moj.global.exception.MOJException
import com.bssm.moj.global.jwt.dto.TokenResponseDto
import jakarta.validation.constraints.NotBlank

@RestController
@RequestMapping("/auth")
class AuthController(
    private val refreshTokenService: RefreshTokenService
) {


    @PutMapping("refreshToken")
    fun refreshToken(@RequestBody refreshToken: @NotBlank String?): TokenResponseDto {
        if(refreshToken == null) throw MOJException(ErrorCode.INVALID_ARGUMENT)
        return refreshTokenService.execute(refreshToken)
    }
}
