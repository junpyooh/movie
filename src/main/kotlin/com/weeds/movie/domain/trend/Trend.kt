package com.weeds.movie.domain.trend

import com.weeds.movie.domain.movie.Movie
import com.weeds.movie.kernel.SimpleAggregateRoot
import java.time.Instant
import javax.persistence.*

@Entity
class Trend(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId")
    val movie: Movie,
    @Column
    val readAt: Instant

) : SimpleAggregateRoot() {
    @Id
    @GeneratedValue
    val id: Long = 0

    companion object {
        fun create(
            movie: Movie,
            readAt: Instant
        ) = Trend(
            movie = movie,
            readAt = readAt
        ).apply {
            registerEvent(TrendEvents(movie))
        }
    }
}