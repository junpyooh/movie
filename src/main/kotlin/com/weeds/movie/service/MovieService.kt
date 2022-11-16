package com.weeds.movie.service

import com.weeds.movie.dto.common.ResultResponse
import com.weeds.movie.dto.movie.MovieDTO

interface MovieService {
    fun getMovie(movieId: Long): ResultResponse<MovieDTO>
}