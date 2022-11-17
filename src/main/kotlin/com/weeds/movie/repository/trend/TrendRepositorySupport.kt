package com.weeds.movie.repository.trend

import com.weeds.movie.domain.trend.Trend
import com.weeds.movie.dto.movie.DayTerm
import com.weeds.movie.dto.trend.TrendSearchKeyword

interface TrendRepositorySupport {
    fun getTrendMovieList(term: DayTerm, keyword: TrendSearchKeyword): MutableList<Trend>
}