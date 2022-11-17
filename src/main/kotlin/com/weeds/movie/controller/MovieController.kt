package com.weeds.movie.controller

import com.weeds.movie.domain.movie.MediaType
import com.weeds.movie.domain.movie.PlayType
import com.weeds.movie.dto.movie.DayTerm
import com.weeds.movie.dto.trend.TrendSearchKeyword
import com.weeds.movie.service.MovieService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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

    @GetMapping("/playType")
    fun getMovieListByPlayType(@RequestParam playType: PlayType) =
        ResponseEntity.status(HttpStatus.OK).body(
            movieService.getMovieListByPlayType(playType)
        )

    @GetMapping("/mediaType")
    fun getMovieListByMediaType(@RequestParam mediaType: MediaType) =
        ResponseEntity.status(HttpStatus.OK).body(
            movieService.getMovieListByMediaType(mediaType)
        )

    @GetMapping("/trailer")
    fun getLatestTrailerListByPlayType(@RequestParam playType: PlayType) =
        ResponseEntity.status(HttpStatus.OK).body(
            movieService.getLatestTrailerListByPlayType(playType)
        )

    @GetMapping("/trend")
    fun getTrendMovieList(
        @RequestParam term: DayTerm,
        @RequestParam condition: TrendSearchKeyword
    ) =
        ResponseEntity.status(HttpStatus.OK).body(
            movieService.getTrendMovieList(term, condition)
        )
}