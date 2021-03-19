package com.sedsoftware.remindies.mvi

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.remindies.domain.RemindiePeriod
import com.sedsoftware.remindies.domain.ShotsDisplayMode
import com.sedsoftware.remindies.domain.entity.Shot
import com.sedsoftware.remindies.mvi.MainStore.*
import kotlinx.datetime.LocalDateTime

interface MainStore : Store<Intent, State, Label> {

    sealed class Intent {
        // Main
        data class SetDisplayMode(val value: ShotsDisplayMode) : Intent()
        object ShowAddNewOne : Intent()
        object HideAddNewOne : Intent()
        object ShowCalendar : Intent()
        object HideCalendar : Intent()

        // AddNewItem
        data class SetTitle(val value: String) : Intent()
        data class SetShotTime(val value: LocalDateTime) : Intent()
        data class SetPeriodEach(val value: Int) : Intent()
        data class SetPeriodical(val value: Boolean) : Intent()
        data class SetPeriodItself(val value: RemindiePeriod) : Intent()
        object Save : Intent()

    }

    data class State(
        // Main
        val addNewOneVisible: Boolean = false,
        val calendarVisible: Boolean = false,
        val emptyScreenVisible: Boolean = false,
        val loadingProgressVisible: Boolean = false,
        val displayMode: ShotsDisplayMode = ShotsDisplayMode.DAY,
        val schedule: List<Shot> = emptyList(),
        // AddNewItem
        val title: String = "",
        val shot: LocalDateTime? = null,
        val periodical: Boolean = false,
        val period: RemindiePeriod = RemindiePeriod.NONE,
        val each: Int = 0,
        val saveEnabled: Boolean = false,
    )

    sealed class Action {
        // Main
        object LoadShots : Action()
    }

    class Label

    sealed class Result {
        // Main
        data class ShotsAvailable(val shots: List<Shot>) : Result()
        data class DisplayModeSelected(val mode: ShotsDisplayMode) : Result()
        object ProgressVisible : Result()
        object ProgressHidden : Result()
        object EmptyScreenVisible : Result()
        object AddNewOneDisplayed : Result()
        object AddNewOneHidden : Result()
        object CalendarDisplayed : Result()
        object CalendarHidden : Result()

        // AddNewItem
        data class TitleEntered(val value: String) : Result()
        data class ShotTimeSelected(val value: LocalDateTime) : Result()
        data class PeriodEachChanged(val value: Int) : Result()
        data class PeriodicalSelected(val value: Boolean) : Result()
        data class PeriodSelected(val value: RemindiePeriod) : Result()
        object ItemCreated : Result()

    }
}
