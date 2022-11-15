package com.weeds.movie.domain.keyword

import com.weeds.movie.domain.kernel.BaseEntity
import com.weeds.movie.domain.movie.Movie
import javax.persistence.*

@Entity
class Keyword(
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