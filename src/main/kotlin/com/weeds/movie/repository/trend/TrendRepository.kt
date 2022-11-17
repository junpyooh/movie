package com.weeds.movie.repository.trend

import com.weeds.movie.domain.trend.Trend
import org.springframework.data.jpa.repository.JpaRepository

interface TrendRepository : JpaRepository<Trend, Long>, TrendRepositorySupport