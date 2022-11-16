package com.weeds.movie.repository.movie

import com.weeds.movie.domain.movie.MediaType
import com.weeds.movie.domain.movie.Movie
import com.weeds.movie.domain.movie.PlayType
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository: JpaRepository<Movie, Long> {
    fun findAllByPlayType(playType: PlayType): List<Movie>

    fun findAllByMediaType(mediaType: MediaType): List<Movie>
}