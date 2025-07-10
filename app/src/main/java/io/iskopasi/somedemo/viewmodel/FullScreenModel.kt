package io.iskopasi.somedemo.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import io.iskopasi.somedemo.managers.PlayerManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class FullScreenModel(
    application: Application,
    private val playerManager: PlayerManager,
) : BaseModel(application = application) {
    private val _player = MutableStateFlow<Player?>(null)

    val player: StateFlow<Player?> = _player

    fun prepareData(link: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _player.value = playerManager.getPlayer(link)
        }
    }
}