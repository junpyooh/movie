package com.weeds.movie.dto.movie

import com.weeds.movie.domain.movie.FileType
import com.weeds.movie.domain.movie.Movie
import com.weeds.movie.domain.movie.MovieAttachments
import java.time.Instant

data class MovieListDTO(
    val movieId: Long,
    val title: String,
    val openedAt: Instant
) {
    companion object {
        fun of(movie: Movie) = MovieListDTO(
            movieId = movie.id,
            title = movie.title,
            openedAt = movie.openedAt
        )
    }
}

data class MovieTrailerListDTO(
    val movieId: Long,
    val title: String,
    val trailerUrl: String?
) {
    companion object {
        fun of(movie: Movie) = MovieTrailerListDTO(
            movieId = movie.id,
            title = movie.title,
            trailerUrl = checkVideo(movie.attachments)?.lastOrNull()?.fileUrl
        )

        private fun checkVideo(attachments: List<MovieAttachments>?) =
            attachments?.filter {
                it.type == FileType.VIDEO
            }
    }
}