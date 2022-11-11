package com.weeds.movie.controller.exceptionHandler

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice(basePackages = ["asia.marketit.influencercard.crm.controller.v1"])
class AdviceExceptionHandler {

    @ExceptionHandler(value = [NoSuchElementException::class])
    fun noSuchElementException(
        e: NoSuchElementException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "존재하지 않는 요청 id 값 입니다."
            this.path = request.requestURI.toString()
            this.timestamp = Instant.now()
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodAArgumentNotValidException(
        e: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()
        e.bindingResult.allErrors.forEach { errorObject ->
            var error = Error().apply {
                this.filed = (errorObject as FieldError).field
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue
            }
            errors.add(error)
        }

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 발생하였습니다."
            this.path = request.requestURI.toString()
            this.timestamp = Instant.now()
            this.errors = errors
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(value = [UnrecognizedPropertyException::class])
    fun unrecognizedPropertyException(
        e: UnrecognizedPropertyException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()

        var error = Error().apply {
            this.message = e.message
        }
        errors.add(error)

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "필터 필드값이 맞지않습니다."
            this.path = request.requestURI.toString()
            this.timestamp = Instant.now()
            this.errors = errors
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }


    @ExceptionHandler(value = [MissingServletRequestParameterException::class])
    fun unrecognizedPropertyException(
        e: MissingServletRequestParameterException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()

        var error = Error().apply {
            this.message = e.message
        }
        errors.add(error)

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "필수 parameter가 없습니다."
            this.path = request.requestURI.toString()
            this.timestamp = Instant.now()
            this.errors = errors
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }


}