package io.iskopasi.somedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import io.iskopasi.somedemo.managers.NavManager
import io.iskopasi.somedemo.managers.RouteDetails
import io.iskopasi.somedemo.managers.RouteMain
import io.iskopasi.somedemo.ui.DetailsScreen
import io.iskopasi.somedemo.ui.MainScreen
import io.iskopasi.somedemo.ui.theme.SomeDemoTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val navManager: NavManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                android.graphics.Color.BLACK,
                android.graphics.Color.BLACK,
            ),
            statusBarStyle = SystemBarStyle.auto(
                android.graphics.Color.BLACK,
                android.graphics.Color.BLACK,
            )
        )

        setContent {
            SomeDemoTheme {
                val backStack = navManager.backStack

                // Forbid exit from app by back button
                BackHandler(enabled = backStack.size == 1) {
                }

                NavDisplay(
                    backStack = backStack,
                    transitionSpec = {
                        // Slide in from right when navigating forward
                        slideInHorizontally(initialOffsetX = { it }) togetherWith
                                slideOutHorizontally(targetOffsetX = { -it })
                    },
                    popTransitionSpec = {
                        // Slide in from left when navigating back
                        slideInHorizontally(initialOffsetX = { -it }) togetherWith
                                slideOutHorizontally(targetOffsetX = { it })
                    },
                    predictivePopTransitionSpec = {
                        // Slide in from left when navigating back
                        slideInHorizontally(initialOffsetX = { -it }) togetherWith
                                slideOutHorizontally(targetOffsetX = { it })
                    },
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = { key ->
                        when (key) {
                            is RouteMain -> NavEntry(key) {
                                MainScreen()
                            }

                            is RouteDetails -> NavEntry(key) {
                                DetailsScreen(id = key.id)
                            }

                            else -> {
                                error("Unknown route: $key")
                            }
                        }
                    }
                )

            }
        }
    }
}
