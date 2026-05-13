package com.example.repro

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

private const val TAG = "Nav3Repro"

class SectionAViewModel : ViewModel() {
    init { Log.d(TAG, "SectionAViewModel created") }
    override fun onCleared() { Log.d(TAG, "SectionAViewModel.onCleared()") }
}

class SectionBViewModel : ViewModel() {
    init { Log.d(TAG, "SectionBViewModel created") }
    override fun onCleared() { Log.d(TAG, "SectionBViewModel.onCleared()") }
}

@Composable
fun SectionAScreen(onNavigateToSectionB: () -> Unit) {
    viewModel<SectionAViewModel>()
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onNavigateToSectionB) {
            Text("Go to SectionB.Root")
        }
    }
}

@Composable
fun SectionBScreen() {
    viewModel<SectionBViewModel>()
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("SectionB.Root — press Back and check Logcat for tag \"$TAG\"")
    }
}
