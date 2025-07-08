package io.iskopasi.somedemo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // declare used Android context
            androidContext(this@App)

            // load modules
            modules(
                managerModules,
                vmModule
            )
        }
    }
}