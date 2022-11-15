package com.weeds.movie.domain.genre

import com.weeds.movie.domain.kernel.BaseEntity
import com.weeds.movie.domain.movie.Movie
import javax.persistence.*

@Entity
class Genre(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId")
    val movie: Movie,
    @Column
    val name: String
) : BaseEntity() {
    @Id
    @GeneratedValue
    val id: Long = 0
}