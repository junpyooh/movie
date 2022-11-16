package com.weeds.movie.service

import com.weeds.movie.dto.common.ResultResponse
import com.weeds.movie.domain.movie.Movie
import com.weeds.movie.domain.trend.Trend
import com.weeds.movie.dto.movie.MovieDTO
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

    private fun createTrend(movie: Movie) {
        trendRepository.save(
            Trend.create(
                movie = movie,
                readAt = Instant.now()
            )
        )
    }
}