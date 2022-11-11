package com.weeds.movie.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity() {


    @CreatedDate
    @Column
    var createdAt: Instant = Instant.MIN
        protected set

    @LastModifiedDate
    @Column
    var updatedAt: Instant = Instant.MIN
        protected set
}