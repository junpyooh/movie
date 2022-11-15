package com.weeds.movie.domain.movie

import javax.persistence.AttributeConverter
import javax.persistence.Converter

enum class MovieState(val value: Int) {
    PENDING(0),     // 개봉 예정
    ONGOING(1),     // 상영 중
    OVERDUE(2)      // 상영 종료
}

@Converter
class MovieStateConverter : AttributeConverter<MovieState, Int> {
    override fun convertToDatabaseColumn(attribute: MovieState?) = attribute?.value
    override fun convertToEntityAttribute(dbData: Int?) = MovieState.values().find { it.value == dbData }
}