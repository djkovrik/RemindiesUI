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
    ) : States()

    data class Calendar(
        val mode: RemindieCalendarMode = RemindieCalendarMode.WEEK,
        val schedule: List<Shot> = emptyList()
    ) : States()

    data class Main(
        val addNewOneVisible: Boolean = false,
        val calendarVisible: Boolean = false,
        val emptyScreenVisible: Boolean = true,
        val schedule: List<Shot> = emptyList()
    ) : States()
}
