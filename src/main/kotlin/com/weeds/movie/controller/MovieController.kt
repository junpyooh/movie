package com.weeds.movie.controller

import com.weeds.movie.service.MovieService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/movies")
class MovieController(
    private val movieService: MovieService
) {
    @GetMapping("/{movieId}")
    fun getMovie(@PathVariable movieId: Long) =
        ResponseEntity.status(HttpStatus.OK).body(
            movieService.getMovie(movieId)
        )
}