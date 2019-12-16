package com.ok.enjoyer.data.remote.api

import com.ok.enjoyer.data.remote.models.response.CommentResponse
import com.ok.enjoyer.data.remote.models.response.PostResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiRepository {

    @GET(ApiEndPoints.POSTS)
    suspend fun getPostsResponse(): Response<List<PostResponse>>

    @GET(ApiEndPoints.COMMENTS)
    suspend fun getComments(@Query(ApiEndPoints.POST_ID) postId: Int): Response<List<CommentResponse>>

}