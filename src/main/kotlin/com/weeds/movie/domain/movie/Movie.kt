package com.weeds.movie.domain.movie

import com.weeds.movie.domain.artist.Artist
import com.weeds.movie.domain.genre.Genre
import com.weeds.movie.domain.kernel.BaseEntity
import com.weeds.movie.domain.keyword.Keyword
import com.weeds.movie.util.isBeforeOrEqual
import java.time.Instant
import javax.persistence.*

@Entity
class Movie(
    @Column
    val title: String,
    @Column
    val realTitle: String?,
    @Column
    val subTitle: String?,
    @Column
    @Convert(converter = MediaTypeConverter::class)
    val mediaType: MediaType,
    @Column
    @Convert(converter = PlayTypeConverter::class)
    val playType: PlayType,
    @Column
    val thumbnailFilename: String,
    @Column
    val thumbnailFileUrl: String,
    @Column
    val summary: String,
    @Column
    val director: Artist,
    @Column
    val openedAt: Instant,
    @Column
    val closedAt: Instant?,
    @Column
    val realLanguage: String,
    @Column
    val productionCost: Int?,
    @Column
    val revenue: Int?,
    @Column
    val filmAgeRate: Int,
    @Column
    val runningTime: Int?
) : BaseEntity() {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    val keywords: MutableList<Keyword>? = mutableListOf()

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    val genres: MutableList<Genre>? = mutableListOf()

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    val attachments: MutableList<MovieAttachments>? = mutableListOf()

    @Column
    @Convert(converter = MovieStateConverter::class)
    var state: MovieState? = updateMovieState()

    @Column
    var hitCount: Int = 0
        protected set

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "movie",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val movieArtist: MutableList<MovieArtist> = mutableListOf()

    @Id
    @GeneratedValue
    val id: Long = 0

    private fun updateMovieState(): MovieState? {
        if (this.playType == PlayType.THEATER) {
            return checkMovieDate()
        }
        return null
    }

    fun checkMovieDate(): MovieState? {
        if (this.openedAt.isAfter(Instant.now())) {
            return MovieState.PENDING
        }
        if (this.openedAt.isBeforeOrEqual(Instant.now())) {
            return MovieState.ONGOING
        }
        if (isMovieOver()) {
            return MovieState.OVERDUE
        }
        return null
    }

    fun isMovieOver() =
        this.closedAt != null
}