package io.iskopasi.somedemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.iskopasi.somedemo.DefaultDispatcher
import io.iskopasi.somedemo.IODispatcher
import io.iskopasi.somedemo.managers.NavManager
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.core.qualifier.named

open class BaseModel(application: Application): AndroidViewModel(application = application), KoinComponent {
    protected val navManager: NavManager by inject()
    protected val ioDispatcher: CoroutineDispatcher = get(named(IODispatcher))
    protected val defaultDispatcher: CoroutineDispatcher = get(named(DefaultDispatcher))

    fun onBack() {
        navManager.onBack()
    }
}