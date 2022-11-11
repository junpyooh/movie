package com.weeds.movie.util

import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime

const val OFFSET_ID = "+09:00"

object Now {
    val month = Instant.now().month
    val hour = Instant.now().hour
}


val Instant.year: Int
    get() = ZonedDateTime
        .ofInstant(this, ZoneOffset.of(OFFSET_ID))
        .year
val Instant.month: Int
    get() = ZonedDateTime
        .ofInstant(this, ZoneOffset.of(OFFSET_ID))
        .monthValue
val Instant.day: Int
    get() = ZonedDateTime
        .ofInstant(this, ZoneOffset.of(OFFSET_ID))
        .dayOfMonth
val Instant.hour: Int
    get() = ZonedDateTime
        .ofInstant(this, ZoneOffset.of(OFFSET_ID))
        .hour
val Instant.minute: Int
    get() = ZonedDateTime
        .ofInstant(this, ZoneOffset.of(OFFSET_ID))
        .minute


fun Instant.isAfterOrEqual(compare: Instant) =
    isAfter(compare).or(equals(compare))

fun Instant.isBeforeOrEqual(compare: Instant) =
    isBefore(compare).or(equals(compare))