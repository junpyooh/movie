package com.weeds.movie.service

import com.weeds.movie.domain.movie.MediaType
import com.weeds.movie.dto.common.ResultResponse
import com.weeds.movie.domain.movie.Movie
import com.weeds.movie.domain.movie.PlayType
import com.weeds.movie.domain.trend.Trend
import com.weeds.movie.dto.common.ListPageResponse
import com.weeds.movie.dto.movie.DayTerm
import com.weeds.movie.dto.movie.MovieDTO
import com.weeds.movie.dto.movie.MovieListDTO
import com.weeds.movie.dto.movie.MovieTrailerListDTO
import com.weeds.movie.dto.trend.TrendSearchKeyword
import com.weeds.movie.repository.movie.MovieRepository
import com.weeds.movie.repository.trend.TrendRepository
import com.weeds.movie.util.findByIdOrThrow
import org.springframework.stereotype.Service
import java.time.Instant
import javax.transaction.Transactional

@Service
class MovieServiceImpl(
    private val movieRepository: MovieRepository,
    private val trendRepository: TrendRepository
) : MovieService {
    @Transactional
    override fun getMovie(movieId: Long): ResultResponse<MovieDTO> {
        val movie = movieRepository.findByIdOrThrow(movieId)
        return ResultResponse(
            result = MovieDTO.of(movie)
        ).also {
            createTrend(movie)
        }
    }

    @Transactional
    override fun getMovieListByPlayType(playType: PlayType): ListPageResponse<MovieListDTO> {
        val response = movieRepository.findAllByPlayType(playType)
            .sortedBy { it.hitCount.dec() }

        return ListPageResponse(
            result = response.map(MovieListDTO::of),
            totalCount = response.size.toLong()
        )
    }

    @Transactional
    override fun getMovieListByMediaType(mediaType: MediaType): ListPageResponse<MovieListDTO> {
        val response = movieRepository.findAllByMediaType(mediaType)

        return ListPageResponse(
            result = response.map(MovieListDTO::of),
            totalCount = response.size.toLong()
        )
    }

    @Transactional
    override fun getLatestTrailerListByPlayType(playType: PlayType): ListPageResponse<MovieTrailerListDTO> {
        val response = movieRepository.findAllByPlayType(playType)
            .sortedByDescending { it.createdAt }

        return ListPageResponse(
            result = response.map(MovieTrailerListDTO::of),
            totalCount = response.size.toLong()
        )
    }

    @Transactional
    override fun getTrendMovieList(term: DayTerm, keyword: TrendSearchKeyword): ListPageResponse<MovieListDTO> {
        val response = trendRepository.getTrendMovieList(term, keyword)
        return ListPageResponse(
            result = response.map {
                MovieListDTO.of(it.movie)
            },
            totalCount = response.size.toLong()
        )
    }

    private fun createTrend(movie: Movie) {
        trendRepository.save(
            Trend.create(
                movie = movie,
                readAt = Instant.now()
            )
        )
    }
}