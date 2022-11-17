package com.weeds.movie.repository.trend

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.weeds.movie.domain.movie.QMovie.movie
import com.weeds.movie.domain.trend.QTrend.trend
import com.weeds.movie.domain.trend.Trend
import com.weeds.movie.dto.movie.DayTerm
import com.weeds.movie.dto.trend.TrendSearchKeyword
import java.time.Instant

class TrendRepositorySupportImpl(
    private val query: JPAQueryFactory
) : TrendRepositorySupport {
    override fun getTrendMovieList(term: DayTerm, keyword: TrendSearchKeyword): MutableList<Trend> {
        return query
            .selectFrom(trend)
            .leftJoin(movie).on(movie.eq(trend.movie))
            .where(
                movie.hitCount.ne(0),
                keyword.startedAt?.let { st ->
                    keyword.endedAt?.let { ed ->
                        checkTerm(term, st, ed)
                    }
                }
            )
            .orderBy(trend.movie.count().sum().desc())
            .fetch()
    }

    private fun checkTerm(term: DayTerm, startedAt: Instant?, endedAt: Instant?): BooleanBuilder {
        val builder = BooleanBuilder()
        if (term == DayTerm.TODAY) {
            builder.and(trend.readAt.eq(Instant.now()))
        }
        if (term == DayTerm.WEEK) {
            greaterThanStartedAt(startedAt).and(
                lessThanEndedAt(endedAt)
            )
        }

        return builder
    }

    private fun greaterThanStartedAt(startedAt: Instant?): BooleanBuilder =
        BooleanBuilder().and(trend.readAt.goe(startedAt))

    private fun lessThanEndedAt(endedAt: Instant?) =
        BooleanBuilder().and(trend.readAt.lt(endedAt))
}