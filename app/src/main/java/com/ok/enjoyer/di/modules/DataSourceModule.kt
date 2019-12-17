package com.ok.enjoyer.di.modules

import com.ok.enjoyer.data.DataManager
import com.ok.enjoyer.data.DataManagerRepository
import com.ok.enjoyer.data.local.prefs.PrefsManager
import com.ok.enjoyer.data.local.prefs.PrefsManagerRepository
import com.ok.enjoyer.data.remote.api.ApiManager
import com.ok.enjoyer.data.remote.api.ApiManagerRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

object DataSourceModule {

    val dataSourceModule = module {

        single<PrefsManager> { PrefsManagerRepository(get()) }

        single<DataManager> { DataManagerRepository(get(), get()) }

        single { ApiManagerRepository(
            get(),
            get(),
            androidApplication()
        ) as ApiManager }

    }

}