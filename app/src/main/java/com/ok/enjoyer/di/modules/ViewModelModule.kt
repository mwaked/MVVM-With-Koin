package com.ok.enjoyer.di.modules

import com.ok.enjoyer.ui.comments.CommentsViewModel
import com.ok.enjoyer.ui.posts.PostsViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object ViewmodelModule {

    val viewmodelModule = module {

        viewModel { PostsViewModel(get()) }
        viewModel { CommentsViewModel(get()) }

    }

}