package com.ok.enjoyer.application

import android.app.Application
import androidx.multidex.MultiDex
import com.ok.enjoyer.di.ApplicationModules.dataSourceModule
import com.ok.enjoyer.di.ApplicationModules.viewmodelModule
import com.prashantsolanki.secureprefmanager.SecurePrefManagerInit
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        installMultidex()

        startKoin()

        initLogger()

        initPrefs()

    }

    private fun startKoin() {
        startKoin(this, listOf(
            dataSourceModule,
            viewmodelModule
        ))
    }

    private fun installMultidex() {
        MultiDex.install(this)
    }

    private fun initPrefs() {
        SecurePrefManagerInit.Initializer(applicationContext)
            .useEncryption(true)
            .initialize()
    }

    private fun initLogger() {
        Timber.plant(Timber.DebugTree())
    }

}