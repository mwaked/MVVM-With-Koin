package com.ok.enjoyer.application.extinsions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes


fun Context.toastLong(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.toastShort(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toastLong(@StringRes message: Int) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.toastShort(@StringRes message: Int) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
