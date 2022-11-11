package com.weeds.movie.controller.exceptionHandler


import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.web.context.request.WebRequest
import org.springframework.web.server.ResponseStatusException

@Component
class CustomErrorAttributes : DefaultErrorAttributes() {
    override fun getErrorAttributes(
        webRequest: WebRequest?,
        options: ErrorAttributeOptions?
    ) = mapOf(
        "message" to super.getErrorAttributes(webRequest, options)["message"],
        "extras" to getError(webRequest)?.let { getExtras(it) }
    )

    private fun getExtras(e: Throwable): Any? =
        when (e) {
            is BindException -> parseFieldError(e.fieldErrors)
            is ResponseStatusException -> processStatusException(e)
            else -> null
        }

    private fun parseFieldError(fieldErrors: List<FieldError>?) =
        fieldErrors?.map {
            mapOf("field" to it.field, "code" to it.code)
        }

    private fun processStatusException(e: ResponseStatusException) =
        mapOf("reason" to e.reason)
}