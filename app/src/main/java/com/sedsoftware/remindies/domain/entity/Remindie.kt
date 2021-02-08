package com.sedsoftware.remindies.domain.entity

import com.sedsoftware.remindies.domain.RemindiePeriod
import com.sedsoftware.remindies.domain.RemindieType
import java.time.LocalDateTime
import java.util.*

data class Remindie(
    val id: Long = 0,
    val timestamp: Long,
    val created: LocalDateTime,
    val shot: LocalDateTime,
    val timeZone: TimeZone,
    val title: String,
    val type: RemindieType,
    val period: RemindiePeriod,
    val each: Int
)
