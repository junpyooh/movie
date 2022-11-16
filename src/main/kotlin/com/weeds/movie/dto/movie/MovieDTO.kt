package com.weeds.movie.dto.movie

import com.weeds.movie.domain.artist.Artist
import com.weeds.movie.domain.movie.FileType
import com.weeds.movie.domain.movie.Movie
import com.weeds.movie.domain.movie.MovieAttachments
import com.weeds.movie.domain.movie.MovieState
import java.time.Instant

data class MovieDTO(
    val movieId: Long,
    val title: String,
    val thumbnailFileUrl: String,
    val openedAt: Instant,
    val filmAgeRate: Int,
    val genre: List<String>?,
    val runningTime: Int?,
    val subTitle: String?,
    val summary: String,
    val artists: List<ArtistDTO>,
    val realTitle: String?,
    val state: MovieState?,
    val realLanguage: String,
    val productionCost: Int?,
    val revenue: Int?,
    val keywords: List<String>?,
    val videoUrl: List<String>?
) {
    companion object {
        fun of(movie: Movie) = MovieDTO(
            movieId = movie.id,
            title = movie.title,
            thumbnailFileUrl = movie.thumbnailFileUrl,
            openedAt = movie.openedAt,
            filmAgeRate = movie.filmAgeRate,
            genre = movie.genres?.map { it.name },
            runningTime = movie.runningTime,
            subTitle = movie.subTitle,
            summary = movie.summary,
            artists = movie.movieArtist.map {
                ArtistDTO.of(it.artist)
            },
            realTitle = movie.realTitle,
            state = movie.state,
            realLanguage = movie.realLanguage,
            productionCost = movie.productionCost,
            revenue = movie.revenue,
            keywords = movie.keywords?.map {
                it.name
            },
            videoUrl = checkVideo(movie.attachments)?.map {
                it.fileUrl
            }
        )

        private fun checkVideo(attachments: List<MovieAttachments>?) =
            attachments?.filter {
                it.type == FileType.VIDEO
            }
    }
}

data class ArtistDTO(
    val artistId: Long,
    val name: String,
    val profileFileUrl: String
) {
    companion object {
        fun of(artist: Artist) = ArtistDTO(
            artistId = artist.id,
            name = artist.name,
            profileFileUrl = artist.profileFileUrl
        )
    }
}