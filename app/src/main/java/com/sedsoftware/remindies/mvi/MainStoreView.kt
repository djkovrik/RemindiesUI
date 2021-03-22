package com.sedsoftware.remindies.mvi

import com.arkivanov.mvikotlin.core.view.MviView
import com.sedsoftware.remindies.domain.RemindiePeriod
import com.sedsoftware.remindies.domain.ShotsDisplayMode
import com.sedsoftware.remindies.domain.entity.Shot
import com.sedsoftware.remindies.mvi.MainStoreView.Event
import com.sedsoftware.remindies.mvi.MainStoreView.Model
import kotlinx.datetime.LocalDateTime

interface MainStoreView : MviView<Model, Event> {

    data class Model(
        val addNewOneVisible: Boolean,
        val calendarVisible: Boolean,
        val emptyScreenVisible: Boolean,
        val loadingProgressVisible: Boolean,
        val displayMode: ShotsDisplayMode,
        val schedule: List<Shot>,
        val title: String,
        val shot: LocalDateTime,
        val periodical: Boolean,
        val period: RemindiePeriod,
        val each: Int,
        val saveEnabled: Boolean,
    )

    sealed class Event {
        data class TitleEntered(val value: String) : Event()
        data class EachPeriodEntered(val value: Int) : Event()
        data class PeriodSelected(val value: RemindiePeriod) : Event()
        data class PeriodicalSelected(val value: Boolean) : Event()
        data class ShotTimeSelected(val value: LocalDateTime) : Event()
        object ShowAddNewItemClicked : Event()
        object HideAddNewItemClicked : Event()
        object ShowCalendarNewItemClicked : Event()
        object HideCalendarNewItemClicked : Event()
    }
}
