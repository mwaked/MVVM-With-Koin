package com.ok.enjoyer.data.remote.api

import com.ok.enjoyer.ui.comments.CommentsState
import com.ok.enjoyer.ui.posts.PostsState

interface ApiManager {
    suspend fun getPostsState(): PostsState
    suspend fun getCommentsForPosts(postId: Int): CommentsState
}