package com.sedsoftware.remindies.mvi

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.remindies.data.RepositoryStub
import com.sedsoftware.remindies.mvi.MainStore.*

object MainStoreFactory {

    val store: MainStore =
        object : MainStore, Store<Intent, State, Label> by DefaultStoreFactory.create(
            name = "MainStore",
            initialState = State(),
            bootstrapper = SimpleBootstrapper<Action>(Action.LoadShots),
            executorFactory = ::MainExecutor,
            reducer = MainReducer
        ) {}

    private object MainReducer : Reducer<State, Result> {
        override fun State.reduce(result: Result): State {
            TODO("Not yet implemented")
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
