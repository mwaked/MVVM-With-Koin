package com.ok.enjoyer.data

import com.ok.enjoyer.data.local.prefs.PrefsManager
import com.ok.enjoyer.data.remote.api.ApiManager
import com.ok.enjoyer.ui.comments.CommentsState
import com.ok.enjoyer.ui.posts.PostsState

class DataManagerRepository(private var apiManager: ApiManager, private var prefsManager: PrefsManager) : DataManager {

    override suspend fun getPostsState(): PostsState {
        return apiManager.getPostsState()
    }

    override suspend fun getCommentsForPosts(postId: Int): CommentsState {
        return apiManager.getCommentsForPosts(postId)
    }

}
