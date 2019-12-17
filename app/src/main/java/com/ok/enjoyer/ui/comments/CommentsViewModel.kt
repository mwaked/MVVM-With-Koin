package com.ok.enjoyer.ui.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ok.enjoyer.application.base.mvvm.CouroutineViewModel
import com.ok.enjoyer.data.DataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext


class CommentsViewModel(private val dataManager: DataManager, uiContext: CoroutineContext = Dispatchers.Main) :
    CouroutineViewModel(uiContext) {

    private val privateState = MutableLiveData<CommentsState>()

    val commentsLiveData: LiveData<CommentsState> = privateState

    fun getComments(id: Int) = launch {
        privateState.value = CommentsState.Loading
        Timber.d("getComments() called....")
        privateState.value = dataManager.getCommentsForPosts(id)
        Timber.d("repository.getComments() executed....${privateState.value}")
    }

}