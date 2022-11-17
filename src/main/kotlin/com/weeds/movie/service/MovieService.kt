package com.weeds.movie.service

import com.weeds.movie.domain.movie.MediaType
import com.weeds.movie.domain.movie.PlayType
import com.weeds.movie.dto.common.ListPageResponse
import com.weeds.movie.dto.common.ResultResponse
import com.weeds.movie.dto.movie.DayTerm
import com.weeds.movie.dto.movie.MovieDTO
import com.weeds.movie.dto.movie.MovieListDTO
import com.weeds.movie.dto.movie.MovieTrailerListDTO
import com.weeds.movie.dto.trend.TrendSearchKeyword

interface MovieService {
    fun getMovie(movieId: Long): ResultResponse<MovieDTO>
    fun getMovieListByPlayType(playType: PlayType): ListPageResponse<MovieListDTO>
    fun getMovieListByMediaType(mediaType: MediaType): ListPageResponse<MovieListDTO>
    fun getLatestTrailerListByPlayType(playType: PlayType): ListPageResponse<MovieTrailerListDTO>
    fun getTrendMovieList(term: DayTerm, keyword: TrendSearchKeyword): ListPageResponse<MovieListDTO>
}