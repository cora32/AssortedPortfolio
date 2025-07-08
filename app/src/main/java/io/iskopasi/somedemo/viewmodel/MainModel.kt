package io.iskopasi.somedemo.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import io.iskopasi.somedemo.SampleDataHolder
import io.iskopasi.somedemo.managers.DataSource
import io.iskopasi.somedemo.managers.room.SampleEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class LoadingState {
    Idle,
    Loading,
    Success,
    Error
}

class MainModel(
    application: Application,
    private val repo: DataSource,
) : BaseModel(application = application) {
    private val _data = repo.dataFlow.map { list -> list.map { it.toSampleDataHolder() } }
        .stateIn(scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )
    private val _state = MutableStateFlow(LoadingState.Idle)

    val data: StateFlow<List<SampleDataHolder>> = _data
    val state: StateFlow<LoadingState> = _state

    fun getData() {
        viewModelScope.launch(ioDispatcher) {
            _state.value = LoadingState.Loading

            if (repo.fetchData()) {
                _state.value = LoadingState.Success
            } else {
                _state.value = LoadingState.Error
            }
        }
    }

    fun onItemSelected(id: Int) {
        navManager.toDetails(id = id)
    }
}

private fun SampleEntity.toSampleDataHolder() = SampleDataHolder (
    imageRes = this.imageRes,
    name = this.name,
    description = this.description,
    link = this.link,
    videoLinks = this.videoList,
)