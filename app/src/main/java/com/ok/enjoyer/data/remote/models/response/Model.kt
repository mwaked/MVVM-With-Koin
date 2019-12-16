package com.ok.enjoyer.data.remote.models.response

import com.google.gson.annotations.SerializedName

open class Model {
    @SerializedName("response") var response: Int? = null
    @SerializedName("message") var message: String? = null
}
