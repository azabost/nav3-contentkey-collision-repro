package com.example.repro.dashboard

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

class MyDashboardViewModel : ViewModel() {
    init { Log.d("Nav3Repro", "MyDashboardViewModel created") }
    override fun onCleared() { Log.d("Nav3Repro", "MyDashboardViewModel.onCleared()") }
}

@Composable
fun MyDashboard(onNavigateToPaymentDashboard: () -> Unit) {
    viewModel<MyDashboardViewModel>()
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onNavigateToPaymentDashboard) {
            Text("Go to Payment Dashboard")
        }
    }
}

