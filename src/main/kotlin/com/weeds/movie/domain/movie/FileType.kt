package com.weeds.movie.domain.movie

import javax.persistence.AttributeConverter
import javax.persistence.Converter

enum class FileType(val value: Int) {
    VIDEO(0),
    BACKGROUND(1),
    POSTER(2)
}

@Converter
class FileTypeConverter : AttributeConverter<FileType, Int> {
    override fun convertToDatabaseColumn(attribute: FileType?) = attribute?.value
    override fun convertToEntityAttribute(dbData: Int?) = FileType.values().find { it.value == dbData }
}