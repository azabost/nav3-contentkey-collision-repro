package com.squareup.payment

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class PaymentDashboardViewModel : ViewModel() {
    init { Log.d("Nav3Repro", "PaymentDashboardViewModel created") }
    override fun onCleared() { Log.d("Nav3Repro", "PaymentDashboardViewModel.onCleared()") }
}

@Composable
fun PaymentDashboardScreen() {
    viewModel<PaymentDashboardViewModel>()
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("PaymentDashboard — press Back and check Logcat for tag \"Nav3Repro\"")
    }
}