package io.iskopasi.somedemo

import androidx.room.Room
import io.iskopasi.somedemo.managers.DataSource
import io.iskopasi.somedemo.managers.NavManager
import io.iskopasi.somedemo.managers.PlayerManager
import io.iskopasi.somedemo.managers.RestApi
import io.iskopasi.somedemo.managers.room.AppDatabase
import io.iskopasi.somedemo.managers.room.SampleDao
import io.iskopasi.somedemo.viewmodel.DetailsModel
import io.iskopasi.somedemo.viewmodel.FullScreenModel
import io.iskopasi.somedemo.viewmodel.MainModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


const val IODispatcher = "IODispatcher"
const val DefaultDispatcher = "DefaultDispatcher"

val managerModules = module {
    single(named(IODispatcher)) {
        Dispatchers.IO
    }

    single(named(DefaultDispatcher)) {
        Dispatchers.Default
    }

    single {
        RestApi()
    }

    single {
        NavManager()
    }

    single {
        PlayerManager(
            application = androidApplication()
        )
    }

    single {
        DataSource(
            dao = get(),
            restApi = get(),
        )
    }

    single {
        NavManager()
    }

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "sample_db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    single<SampleDao> {
        get<AppDatabase>().dao()
    }
}


val vmModule = module {
    viewModel {
        MainModel(
            application = androidApplication(),
            repo = get(),
            )
    }
    viewModel {
        DetailsModel(
            application = androidApplication(),
            dataSource = get(),
            playerManager = get()
        )
    }
    viewModel {
        FullScreenModel(
            application = androidApplication(),
            playerManager = get()
        )
    }
}