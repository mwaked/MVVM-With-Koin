package com.ok.enjoyer.ui.posts

import com.ok.enjoyer.data.remote.models.response.PostResponse

sealed class PostsState {
    object Loading : PostsState()
    data class Error(val message: String?) : PostsState()
    data class PostsLoaded(val posts: List<PostResponse>) : PostsState()
}