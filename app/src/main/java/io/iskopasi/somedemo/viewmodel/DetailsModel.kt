package io.iskopasi.somedemo.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import io.iskopasi.somedemo.SampleDataHolder
import io.iskopasi.somedemo.managers.DataSource
import io.iskopasi.somedemo.managers.PlayerManager
import io.iskopasi.somedemo.unknownSampleData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class PlayerHolder(val link: String, val player: Player)

class DetailsModel(
    application: Application,
    private val dataSource: DataSource,
    private val playerManager: PlayerManager,
) : BaseModel(application = application) {
    private val _item = MutableStateFlow(
        unknownSampleData
    )
    private val _players = MutableStateFlow<List<PlayerHolder>>(emptyList())

    val players: StateFlow<List<PlayerHolder>> = _players
    val item: StateFlow<SampleDataHolder> = _item

    fun prepareData(id: Int) {
        viewModelScope.launch(defaultDispatcher) {
            val data = dataSource.getData(id).toSampleDataHolder()
            _item.value = data

            withContext(Dispatchers.Main) {
                _players.value = data.videoLinks.toPlayerHolderList()
            }
        }
    }

    fun onVideoClick(link: String) {
        navManager.toFullScreen(link = link)
    }

    private fun List<String>.toPlayerHolderList() = this.map {
        PlayerHolder(
            link = it,
            player = playerManager.getPlayer(it)
        )
    }
}