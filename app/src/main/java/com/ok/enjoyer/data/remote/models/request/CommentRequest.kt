package com.ok.enjoyer.data.remote.models.request

import com.google.gson.annotations.SerializedName

data class CommentRequest(@SerializedName("postId") val postId: Int)