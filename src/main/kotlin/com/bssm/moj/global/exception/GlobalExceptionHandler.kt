package com.bssm.moj.global.exception

import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MOJException::class)
    fun handleCustomException(e: MOJException): ResponseEntity<*> {
        return ResponseEntity<ExceptionResponse>(
            ExceptionResponse(
                e.errorCode.status,
                e.message?:"Unknown Error"
            ), HttpStatus.valueOf(e.errorCode.status)
        )
    }


    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleConstraintViolation(e: ConstraintViolationException): ResponseEntity<*> {
        println(e.message)
        val errorMap: MutableMap<String, String> = HashMap()
        for (violation in e.getConstraintViolations()) {
            errorMap[violation.propertyPath.toString()] = violation.message
        }
        return ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleServerException(ex: Exception?): ResponseEntity<ExceptionResponse> {
        if (ex != null) {
            println(ex.message)
        }
        return ResponseEntity<ExceptionResponse>(
            ExceptionResponse(
                500, "Internal Server Error"
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}
