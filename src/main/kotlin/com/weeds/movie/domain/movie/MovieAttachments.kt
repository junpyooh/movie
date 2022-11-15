package com.weeds.movie.domain.movie

import com.weeds.movie.domain.kernel.BaseEntity
import javax.persistence.*

@Entity
class MovieAttachments(
    @Column
    val fileName: String,
    @Column
    val fileUrl: String,
    @Column
    val ext: String,
    @Column
    val size: String,
    @Column
    val type: FileType,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId")
    val movie: Movie
):BaseEntity() {
    @Id
    @GeneratedValue
    val id: Long = 0
}