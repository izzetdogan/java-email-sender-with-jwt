package com.example.security.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Token @JvmOverloads constructor(
    @Id
    val id:String?=null,
    val token:String,
    val createdAt: LocalDateTime,
    val expiredAt: LocalDateTime,
    val confirmedAt: LocalDateTime?=null,
    val user: User
)
