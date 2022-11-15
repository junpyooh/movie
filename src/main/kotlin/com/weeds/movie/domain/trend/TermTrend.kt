package com.weeds.movie.domain.trend

import com.weeds.movie.domain.kernel.BaseEntity
import com.weeds.movie.domain.movie.Movie
import java.time.Instant
import javax.persistence.*

@Entity
class TermTrend(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId")
    val movie: Movie,
    @Column
    val readAt: Instant

) : BaseEntity() {
    @Id
    @GeneratedValue
    val id: Long = 0
}