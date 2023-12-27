package com.bssm.moj.global.exception

class MOJException(
    val errorCode: ErrorCode
):RuntimeException(errorCode.message) {
}
