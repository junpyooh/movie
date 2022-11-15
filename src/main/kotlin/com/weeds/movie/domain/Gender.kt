package com.weeds.movie.domain

import javax.persistence.AttributeConverter
import javax.persistence.Converter

enum class Gender(val value: Int) {
    MALE(0),
    FEMALE(1),
    OTHER(3)
}

@Converter
class GenderConverter : AttributeConverter<Gender, Int> {
    override fun convertToDatabaseColumn(attribute: Gender?) = attribute?.value
    override fun convertToEntityAttribute(dbData: Int?) = Gender.values().find { it.value == dbData }
}