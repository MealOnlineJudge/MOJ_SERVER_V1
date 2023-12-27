package com.bssm.moj.global.exception

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException


class ExceptionFilter : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: MOJException) {
            writeErrorCode(response, e.errorCode.status, e.message ?: "Unknown Error")
        } catch (e: Exception) {
            e.printStackTrace()
            writeErrorCode(response, 500, "Internal Server Error")
        }
    }

    @Throws(IOException::class)
    private fun writeErrorCode(response: HttpServletResponse, status: Int, message:String) {
        val errorResponse = ExceptionResponse(
            status,message
        )
        response.status = errorResponse.status
        response.characterEncoding = "UTF-8"
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(errorResponse.toString())
    }
}
