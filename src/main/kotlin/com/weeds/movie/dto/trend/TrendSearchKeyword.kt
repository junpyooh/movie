package com.weeds.movie.dto.trend

import java.time.Instant

data class TrendSearchKeyword(
    val startedAt: Instant? = null,
    val endedAt: Instant? = null
)