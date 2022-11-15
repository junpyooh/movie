package com.weeds.movie.domain.movie

import com.weeds.movie.domain.artist.Artist
import com.weeds.movie.domain.kernel.BaseEntity
import javax.persistence.*

@Entity
class MovieArtist(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId")
    val movie: Movie,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artistId")
    val artist: Artist
) : BaseEntity() {
    @Id
    @GeneratedValue
    val id: Long = 0
}