package com.weeds.movie.domain.kernel

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
open class EnumConverter<T : EnumPersistable>(private val values: Array<T>) : AttributeConverter<T, Int> {
    override fun convertToDatabaseColumn(attribute: T): Int = attribute.value

    override fun convertToEntityAttribute(dbData: Int?) =
        values.find { it.value == dbData }
}

interface EnumPersistable {
    val value: Int
}