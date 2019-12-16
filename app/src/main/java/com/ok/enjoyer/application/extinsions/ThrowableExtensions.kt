package com.ok.enjoyer.application.extinsions

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import com.ok.enjoyer.R
import com.ok.enjoyer.data.remote.models.response.Model
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * Get api error message from Throwable
 */
fun Throwable.getApiErrorMessage(context: Context): String {
    val response = 500
    when (this) {
        is HttpException -> {
            if (this.response()?.errorBody() == null) {
                return context.getString(R.string.text_no_network_found)
            } else {
                val apiError: Model?

                try {
                    apiError = Gson().fromJson(this.response()?.errorBody()?.string(), Model::class.java)
                }
                catch (e: JsonIOException) {
                    return this.response()?.errorBody()?.string() ?: context.getString(R.string.text_no_network_found)
                }
                catch (e: JsonSyntaxException){
                    return this.response()?.errorBody()?.string() ?: context.getString(R.string.text_no_network_found)
                }

                if (apiError != null) {
                    return  apiError.message ?: context.getString(R.string.text_no_network_found)
                } else {
                    context.getString(R.string.text_no_network_found)
                }
            }
        }
        is UnknownHostException -> context.getString(R.string.text_no_network_found)
        else -> return response.toString()
    }
    return context.getString(R.string.text_no_network_found)
}