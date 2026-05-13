package com.example.repro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay

// Set to true to apply the workaround and verify that ViewModel.onCleared() IS called.
private const val USE_FIX = false

// Reproduces: ViewModel.onCleared() not called when multiple data object nav keys
// share the same simple class name (e.g. SectionA.Root and SectionB.Root both have
// toString() == "Root"), causing contentKey collision in NavEntry.
//
// Steps to reproduce:
//   1. Set USE_FIX = false (default).
//   2. Launch the app — SectionAScreen is shown, SectionAViewModel is created.
//   3. Tap "Go to SectionB.Root" — SectionBViewModel is created.
//   4. Press Back — SectionBScreen is popped.
//
// Expected: "SectionBViewModel.onCleared()" appears in Logcat (tag: Nav3Repro).
// Actual:   nothing appears — onCleared() is never called.
//
// To verify the fix: set USE_FIX = true and repeat — onCleared() will be called.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val backStack = rememberNavBackStack(AppRoute.SectionA.Root)

                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryDecorators = listOf(
                        rememberSaveableStateHolderNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator(),
                    ),
                    entryProvider = entryProvider {
                        entry<AppRoute.SectionA.Root>(clazzContentKey = ::contentKey) {
                            SectionAScreen(
                                onNavigateToSectionB = { backStack.add(AppRoute.SectionB.Root) }
                            )
                        }
                        entry<AppRoute.SectionB.Root>(clazzContentKey = ::contentKey) {
                            SectionBScreen()
                        }
                    },
                )
            }
        }
    }
}

// USE_FIX = false: default behavior — key.toString() returns "Root" for all Root objects,
//                  causing contentKey collision between SectionA.Root and SectionB.Root.
// USE_FIX = true:  workaround — combines qualifiedName and toString(), mirroring NavKeySerializer.
//                  Unique for both data object (via qualifiedName) and data class (via toString()).
//                  String satisfies the contentKey saveability requirement on Android.
private fun contentKey(key: Any): Any =
    if (USE_FIX) "${checkNotNull(key::class.qualifiedName)}:$key" else key.toString()
