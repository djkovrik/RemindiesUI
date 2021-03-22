package com.sedsoftware.remindies.mvi

object Mappers {

    val viewEventToIntent: MainStoreView.Event.() -> MainStore.Intent = {
        when (this) {
            is MainStoreView.Event.TitleEntered -> MainStore.Intent.SetTitle(value)
            is MainStoreView.Event.EachPeriodEntered -> MainStore.Intent.SetPeriodEach(value)
            is MainStoreView.Event.PeriodSelected -> MainStore.Intent.SetPeriodItself(value)
            is MainStoreView.Event.PeriodicalSelected -> MainStore.Intent.SetPeriodical(value)
            is MainStoreView.Event.ShotTimeSelected -> MainStore.Intent.SetShotTime(value)
            is MainStoreView.Event.ShowAddNewItemClicked -> MainStore.Intent.ShowAddNewOne
            is MainStoreView.Event.HideAddNewItemClicked -> MainStore.Intent.HideAddNewOne
            is MainStoreView.Event.ShowCalendarClicked -> MainStore.Intent.ShowCalendar
            is MainStoreView.Event.HideCalendarClicked -> MainStore.Intent.HideCalendar
        }
    }

    val stateToViewModel: MainStore.State.() -> MainStoreView.Model = {
        MainStoreView.Model(
            addNewOneVisible = this.addNewOneVisible,
            calendarVisible = this.calendarVisible,
            emptyScreenVisible = this.emptyScreenVisible,
            loadingProgressVisible = this.loadingProgressVisible,
            displayMode = this.displayMode,
            schedule = this.schedule,
            title = this.title,
            shot = this.shot,
            periodical = this.periodical,
            period = this.period,
            each = this.each,
            saveEnabled = this.saveEnabled
        )
    }
}
