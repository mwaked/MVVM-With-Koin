package com.ok.enjoyer.application.extinsions

import android.content.Intent

inline fun <reified T> Intent.getObject(): T? {
    return if (getSerializableExtra(T::class.java.simpleName) == null) {
        null
    } else {
        getSerializableExtra(T::class.java.simpleName) as T
    }
}


fun Intent.getValidString(key: String): String? {
    return extras?.let {
        if (it.getString(key) != null) {
            it.getString(key)
        } else {
            null
        }
    }
}

fun Intent.getValidInt(key: String): Int? {
    return extras?.let {
        if (it.getInt(key) != null) {
            it.getInt(key)
        } else {
            null
        }
    }
}

fun Intent.getValidBoolean(key: String): Boolean? {
    return extras?.let {
        if (it.getBoolean(key) != null) {
            it.getBoolean(key)
        } else {
            null
        }
    }
}

fun Intent.getValidDouble(key: String): Double? {
    return extras?.let {
        if (it.getDouble(key) != null) {
            it.getDouble(key)
        } else {
            null
        }
    }
}

fun Intent.getValidFloat(key: String): Float? {
    return extras?.let {
        if (it.getFloat(key) != null) {
            it.getFloat(key)
        } else {
            null
        }
    }
}

fun Intent.getValidLong(key: String): Long? {
    return extras?.let {
        if (it.getLong(key) != null) {
            it.getLong(key)
        } else {
            null
        }
    }
}