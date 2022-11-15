package com.weeds.movie.domain.movie

import javax.persistence.AttributeConverter
import javax.persistence.Converter

enum class MediaType(val value: Int) {
    MOVIE(0),
    TV(1)
}

@Converter
class MediaTypeConverter : AttributeConverter<MediaType, Int> {
    override fun convertToDatabaseColumn(attribute: MediaType?) = attribute?.value
    override fun convertToEntityAttribute(dbData: Int?) = MediaType.values().find { it.value == dbData }
}