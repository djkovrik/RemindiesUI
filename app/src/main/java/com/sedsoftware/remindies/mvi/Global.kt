package com.sedsoftware.remindies.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.sedsoftware.remindies.mvi.MainStore.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
object Global {

    private val state = MainStoreFactory().create()

    val statesFlow: Flow<State>
        get() = state.states
}
