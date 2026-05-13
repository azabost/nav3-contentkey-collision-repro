package com.example.repro

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute : NavKey {

    @Serializable
    sealed interface SectionA : AppRoute {
        @Serializable
        data object Root : SectionA  // toString() == "Root"
    }

    @Serializable
    sealed interface SectionB : AppRoute {
        @Serializable
        data object Root : SectionB  // toString() == "Root"
    }
}
