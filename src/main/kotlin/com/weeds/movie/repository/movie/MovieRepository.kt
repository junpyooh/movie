package com.weeds.movie.repository.movie

import com.weeds.movie.domain.movie.Movie
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository: JpaRepository<Movie, Long> {
}