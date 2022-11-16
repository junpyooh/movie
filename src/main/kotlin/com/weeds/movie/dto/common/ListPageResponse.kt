package com.weeds.movie.dto.common

data class ListPageResponse<T>(
    val result: List<T> = listOf(),
    var totalCount: Long = 0
)