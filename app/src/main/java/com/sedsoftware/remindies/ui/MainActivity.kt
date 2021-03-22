package com.sedsoftware.remindies.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.sedsoftware.remindies.mvi.Global
import com.sedsoftware.remindies.mvi.MainStore
import com.sedsoftware.remindies.ui.ui.RemindiesUITheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemindiesUITheme {
                Surface(color = MaterialTheme.colors.background) {
                    val state by Global.statesFlow.collectAsState(initial = MainStore.State())
                    Log.d("composedebug", "Render state: $state")
                    MainScreen(state)
                }
            }
        }
    }
}

@Composable
fun MainScreen(state: MainStore.State) {
    Text("Wohoo!")
}
