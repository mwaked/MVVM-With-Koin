package com.ok.enjoyer.di

import com.ok.enjoyer.BuildConfig
import com.ok.enjoyer.ui.comments.CommentsViewModel
import com.ok.enjoyer.ui.posts.PostsViewModel
import com.ok.enjoyer.application.helpers.network.NetworkState
import com.ok.enjoyer.data.DataManager
import com.ok.enjoyer.data.DataManagerRepository
import com.ok.enjoyer.data.local.prefs.PrefsManagerRepository
import com.ok.enjoyer.data.local.prefs.PrefsManager
import com.ok.enjoyer.data.remote.api.ApiManager
import com.ok.enjoyer.data.remote.api.ApiManagerRepository
import com.ok.enjoyer.data.remote.api.ApiRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApplicationModules {

    val dataSourceModule = module {

        single<PrefsManager> { PrefsManagerRepository(get()) }

        single<DataManager> { DataManagerRepository(get(), get()) }

        single { createRetrofitInstance() }

        single { get<Retrofit>().create(ApiRepository::class.java) }

        single {
            NetworkState(
                androidApplication()
            )
        }

        single { ApiManagerRepository(
            get(),
            get(),
            androidApplication()
        ) as ApiManager }

    }

    val viewmodelModule = module {

        viewModel { PostsViewModel(get()) }
        viewModel { CommentsViewModel(get()) }

    }

    private fun createRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}