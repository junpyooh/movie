package com.weeds.movie.kernel

import com.weeds.movie.domain.kernel.BaseEntity
import javax.persistence.MappedSuperclass
import javax.persistence.Transient

@MappedSuperclass
abstract class SimpleAggregateRoot():BaseEntity() {
    @Transient
    private val domainEvents: MutableList<Any> = mutableListOf()

    protected fun registerEvent(event: Any) {
        domainEvents.add(event)
    }
}