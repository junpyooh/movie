package com.weeds.movie.domain.user

import com.weeds.movie.domain.kernel.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
    @Column
    val credentialId: String,
    @Column
    val password: String,
    @Column
    val email: String
) : BaseEntity() {
    @Id
    @GeneratedValue
    val id: Long = 0
}