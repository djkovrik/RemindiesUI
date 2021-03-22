package com.sedsoftware.remindies.mvi

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.remindies.data.RepositoryStub
import com.sedsoftware.remindies.mvi.MainStore.*

class MainStoreFactory {

    fun create(): MainStore =
        object : MainStore, Store<Intent, State, Label> by DefaultStoreFactory.create(
            name = "MainStore",
            initialState = State(),
            bootstrapper = SimpleBootstrapper<Action>(Action.LoadShots),
            executorFactory = ::MainExecutor,
            reducer = MainReducer
        ) {}

    private object MainReducer : Reducer<State, Result> {
        override fun State.reduce(result: Result): State = when (result) {
            is Result.ShotsAvailable -> copy(
                schedule = result.value
            )
            is Result.DisplayModeSelected -> copy(
                displayMode = result.value
            )
            is Result.ProgressVisible -> copy(
                loadingProgressVisible = true
            )
            is Result.ProgressHidden -> copy(
                loadingProgressVisible = false
            )
            is Result.EmptyScreenVisible -> copy(
                emptyScreenVisible = true
            )
            is Result.EmptyScreenHidden -> copy(
                emptyScreenVisible = false
            )
            is Result.AddNewOneDisplayed -> copy(
                addNewOneVisible = true
            )
            is Result.AddNewOneHidden -> copy(
                addNewOneVisible = false
            )
            is Result.CalendarDisplayed -> copy(
                calendarVisible = true
            )
            is Result.CalendarHidden -> copy(
                calendarVisible = false
            )
            is Result.TitleEntered -> copy(
                title = result.value
            )
            is Result.ShotTimeSelected -> copy(
                shot = result.value
            )
            is Result.PeriodEachChanged -> copy(
                each = result.value
            )
            is Result.PeriodicalSelected -> copy(
                periodical = result.value
            )
            is Result.PeriodSelected -> copy(
                period = result.value
            )
            is Result.ItemCreated -> this
        }
    }

    private class MainExecutor :
        SuspendExecutor<Intent, Action, State, Result, Label>() {

        override suspend fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.LoadShots -> {
                    dispatch(Result.ProgressVisible)
                    val shots = RepositoryStub.getShots()
                    dispatch(Result.ProgressHidden)

                    if (shots.isNotEmpty()) {
                        dispatch(Result.EmptyScreenHidden)
                        dispatch(Result.ShotsAvailable(shots))
                    } else {
                        dispatch(Result.EmptyScreenVisible)
                    }
                }
            }
        }

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                // Main
                is Intent.SetDisplayMode -> Result.DisplayModeSelected(intent.value)
                is Intent.ShowAddNewOne -> dispatch(Result.AddNewOneDisplayed)
                is Intent.HideAddNewOne -> dispatch(Result.AddNewOneHidden)
                is Intent.ShowCalendar -> dispatch(Result.CalendarDisplayed)
                is Intent.HideCalendar -> dispatch(Result.CalendarHidden)
                // AddNewItem
                is Intent.SetTitle -> dispatch(Result.TitleEntered(intent.value))
                is Intent.SetShotTime -> dispatch(Result.ShotTimeSelected(intent.value))
                is Intent.SetPeriodEach -> dispatch(Result.PeriodEachChanged(intent.value))
                is Intent.SetPeriodical -> dispatch(Result.PeriodicalSelected(intent.value))
                is Intent.SetPeriodItself -> dispatch(Result.PeriodSelected(intent.value))
                is Intent.Save -> dispatch(Result.ItemCreated)
            }
        }
    }
}
