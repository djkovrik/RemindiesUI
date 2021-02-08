package com.sedsoftware.remindies.domain

import com.sedsoftware.remindies.domain.entity.Shot
import java.time.LocalDateTime


sealed class States {

    data class AddNew(
        val title: String = "",
        val shot: LocalDateTime? = null,
        val periodical: Boolean = false,
        val saveEnabled: Boolean = false,
        val period: RemindiePeriod = RemindiePeriod.NONE,
        val each: Int = 0
    )

    data class Calendar(
        val schedule: List<Shot> = emptyList()
    )
}
