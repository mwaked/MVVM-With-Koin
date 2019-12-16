package com.ok.enjoyer.data.remote.api

import android.app.Application
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import com.ok.enjoyer.R
import com.ok.enjoyer.application.extinsions.getApiErrorMessage
import com.ok.enjoyer.ui.comments.CommentsState
import com.ok.enjoyer.ui.posts.PostsState
import com.ok.enjoyer.application.helpers.network.NetworkState
import com.ok.enjoyer.data.remote.models.response.Model
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException

class ApiManagerRepository(
    private val api: ApiRepository,
    private val networkState: NetworkState,
    private val application: Application,
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ApiManager {

    /**
     * Get posts
     */
    override suspend fun getPostsState(): PostsState = withContext(backgroundDispatcher) {
        if (!networkState.isConnected()) {
            return@withContext PostsState.Error(application.getString(R.string.not_connected))
        }

        val response = try {
            api.getPostsResponse()
        } catch (e:Throwable){
            //Sending a generic exception to the view for now

            Timber.e(e)
            return@withContext PostsState.Error(e.getApiErrorMessage(application))
        }

        return@withContext if (response.isSuccessful) {
            response.body()?.let {
                PostsState.PostsLoaded(it)
            } ?: PostsState.Error(application.getString(R.string.emty_body))
        } else {
            Timber.e(response.message())
            PostsState.Error(response.message())
        }
    }

    /**
     * Get comments for posts
     */
    override suspend fun getCommentsForPosts(postId: Int): CommentsState = withContext(backgroundDispatcher) {
        if (!networkState.isConnected()) {
            return@withContext CommentsState.Error(application.getString(R.string.not_connected))
        }

        val response =try {
            api.getComments(postId)
        } catch (e:Throwable){
            //Sending a generic exception to the view for now
            Timber.e(e)
            return@withContext CommentsState.Error(e.getApiErrorMessage(application))
        }

        return@withContext if (response.isSuccessful) {
            response.body()?.let {
                CommentsState.PostsLoaded(it)
            } ?: CommentsState.Error(application.getString(R.string.emty_body))
        } else {
            Timber.e(response.message())
            CommentsState.Error(response.message())
        }
    }

}
