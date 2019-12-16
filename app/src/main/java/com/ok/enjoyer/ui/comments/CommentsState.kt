package com.ok.enjoyer.ui.comments

import com.ok.enjoyer.data.remote.models.response.CommentResponse

sealed class CommentsState {
    object Loading : CommentsState()
    data class Error(val message: String?) : CommentsState()
    data class PostsLoaded(val comments: List<CommentResponse>) : CommentsState()
}