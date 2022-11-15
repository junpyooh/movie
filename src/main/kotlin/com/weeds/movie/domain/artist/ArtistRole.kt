package com.weeds.movie.domain.artist

import javax.persistence.AttributeConverter
import javax.persistence.Converter

enum class ArtistRole(val value: Int) {
    DIRECTOR(0),
    ACTOR(1)
}

@Converter
class ArtistRoleConverter : AttributeConverter<ArtistRole, Int> {
    override fun convertToDatabaseColumn(attribute: ArtistRole?) = attribute?.value
    override fun convertToEntityAttribute(dbData: Int?) = ArtistRole.values().find { it.value == dbData }
}