package com.bssm.moj.global.exception

class ExceptionResponse(
    val status:Int,
    val message:String
) {
    override fun toString(): String {
        return "ExceptionResponse(status=$status, message='$message')"
    }
}
