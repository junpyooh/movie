package com.weeds.movie.controller.exceptionHandler

import java.time.Instant

data class ErrorResponse(
    var resultCode: String? = null,
    var httpStatus: String? = null,
    var httpMethod: String? = null,
    var message: String? = null,
    var path: String? = null,
    var timestamp: Instant? = null,
    var errors: MutableList<Error>? = mutableListOf()
)

data class Error(
    var filed: String? = null,
    var message: String? = null,
    var value: Any? = null
)