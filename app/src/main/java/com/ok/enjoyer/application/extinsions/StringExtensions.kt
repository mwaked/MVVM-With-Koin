package com.ok.enjoyer.application.extinsions

import android.util.Patterns

fun String.isValid(): Boolean {
    return this.isNotEmpty() && this.length > 3
}

fun String.isMatch(retryPassword: String): Boolean {
    return this.contentEquals(retryPassword)
}

fun String.isValidEmailAddress(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidUrl(): Boolean {
    return Patterns.WEB_URL.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.length >= 6
}

fun String.toNumericString(): String {
    val chars = CharArray(this.length)
    for (i in this.indices) {
        var ch = this[i]
        if (ch.toInt() in 0x0660..0x0669)
            ch -= (0x0660 - '0'.toInt())
        else if (ch.toInt() in 0x06f0..0x06F9)
            ch -= (0x06f0 - '0'.toInt())
        chars[i] = ch
    }
    return String(chars)
}