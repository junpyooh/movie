package com.weeds.movie.domain.artist

import com.weeds.movie.domain.Gender
import com.weeds.movie.domain.GenderConverter
import com.weeds.movie.domain.kernel.BaseEntity
import com.weeds.movie.domain.movie.MovieArtist
import java.time.Instant
import javax.persistence.*

@Entity
class Artist(
    @Column
    val name: String,
    @Column
    @Convert(converter = GenderConverter::class)
    val gender: Gender,
    @Column
    val birth: Instant,
    @Column
    val birthPlace: String,
    @Column
    val summary: String,
    @Column
    @Convert(converter = ArtistRoleConverter::class)
    val role: ArtistRole,
    @Column
    val profileFileName: String,
    @Column
    val profileFileUrl: String
) : BaseEntity() {
    @Column
    var anotherName: String? = null
        protected set

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "artist")
    val movieArtist: MutableList<MovieArtist> = mutableListOf()

    @Id
    @GeneratedValue
    val id: Long = 0
}