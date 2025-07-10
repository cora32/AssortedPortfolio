package io.iskopasi.somedemo.managers

import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


@Serializable
data object RouteMain : NavKey

@Serializable
data class RouteDetails(val id: Int) : NavKey

@Serializable
data class RouteFullScreen(val link: String) : NavKey


class NavManager {
    val backStack = mutableStateListOf<NavKey>(RouteMain)

    fun onBack() {
        if (backStack.size > 1)
            backStack.removeLastOrNull()
    }

    fun toDetails(id: Int) {
        backStack.add(RouteDetails(id = id))
    }

    fun toFullScreen(link: String) {
        backStack.add(RouteFullScreen(link = link))
    }
}