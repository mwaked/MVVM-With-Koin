package com.ok.enjoyer.application.extinsions

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun Context.showLongSnackBar(rootView: View, message: String) = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()


fun Context.showLongSnackBar(rootView: View, message: Int) = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()

fun Context.showLongSnackBarWithAction(rootView: View, message: String, actionTitle: String, click: View.OnClickListener) =
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).setAction(actionTitle, click).show()

fun Context.showLongSnackBarWithAction(rootView: View, message: String, actionTitle: Int, click: View.OnClickListener) = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).setAction(actionTitle, click).show()
