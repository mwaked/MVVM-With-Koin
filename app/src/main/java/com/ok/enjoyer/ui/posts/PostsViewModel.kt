package com.ok.enjoyer.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ok.enjoyer.application.base.mvvm.CouroutineViewModel
import com.ok.enjoyer.data.DataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class PostsViewModel(private val dataManager: DataManager, uiContext: CoroutineContext = Dispatchers.Main) :
    CouroutineViewModel(uiContext) {

    private val privateState = MutableLiveData<PostsState>()

    val postLiveData: LiveData<PostsState> = privateState

    fun refreshPosts() = launch {
        privateState.value = PostsState.Loading
        Timber.d("getPostsState() called....")
        privateState.value = dataManager.getPostsState()
        Timber.d("repository.getPostsState() executed....")
    }

}