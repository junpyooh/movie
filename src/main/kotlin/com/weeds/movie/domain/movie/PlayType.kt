package com.weeds.movie.domain.movie

import javax.persistence.AttributeConverter
import javax.persistence.Converter

enum class PlayType(val value: Int) {
    STREAMING(0),
    TV(1),
    RENTAL(2),
    THEATER(3)
}

@Converter
class PlayTypeConverter : AttributeConverter<PlayType, Int> {
    override fun convertToDatabaseColumn(attribute: PlayType?) = attribute?.value
    override fun convertToEntityAttribute(dbData: Int?) = PlayType.values().find { it.value == dbData }
}