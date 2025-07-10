package io.iskopasi.somedemo.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import io.iskopasi.somedemo.R
import io.iskopasi.somedemo.SampleDataHolder
import io.iskopasi.somedemo.managers.DataSource
import io.iskopasi.somedemo.managers.PlayerManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsModel(
    application: Application,
    private val dataSource: DataSource,
    private val playerManager: PlayerManager,
) : BaseModel(application = application) {
    private val _item = MutableStateFlow(
        SampleDataHolder(
            imageRes = R.drawable.shaders,
            name = "",
            description = "",
            link = "",
            videoLinks = emptyList()
        )
    )
    private val _players = MutableStateFlow<List<Player>>(emptyList())

    val players: StateFlow<List<Player>> = _players
    val item: StateFlow<SampleDataHolder> = _item

    fun prepareData(id: Int) {
        viewModelScope.launch(defaultDispatcher) {
            val data = dataSource.getData(id).toSampleDataHolder()
            _item.value = data

            _players.value = data.videoLinks.toPlayerList()
        }
    }

    private fun List<String>.toPlayerList() = this.map { it.toPlayer() }

    private fun String.toPlayer(): Player {
        val application = getApplication<Application>()
        return playerManager.getPlayer(this)
    }
}