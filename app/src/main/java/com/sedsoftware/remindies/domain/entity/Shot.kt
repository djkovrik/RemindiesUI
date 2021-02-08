package com.sedsoftware.remindies.domain.entity

import java.time.LocalDateTime

data class Shot(
    val remindie: Remindie,
    val planned: LocalDateTime,
    val isFired: Boolean
)
