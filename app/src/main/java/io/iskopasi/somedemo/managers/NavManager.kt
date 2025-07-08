package io.iskopasi.somedemo.managers

import androidx.compose.runtime.mutableStateListOf
import kotlinx.serialization.Serializable
import androidx.navigation3.runtime.NavKey


@Serializable
data object RouteMain : NavKey

@Serializable
data class RouteDetails(val id: Int) : NavKey

class NavManager {
    private val _backStack = mutableStateListOf<NavKey>(RouteMain)

    fun onBack() {
        if (_backStack.size > 1)
            _backStack.removeLastOrNull()
    }

    fun toDetails(id: Int) {
        _backStack.add(RouteDetails(id = id))
    }
}