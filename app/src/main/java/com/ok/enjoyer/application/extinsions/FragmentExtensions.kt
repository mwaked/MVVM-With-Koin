package com.ok.enjoyer.application.extinsions

import androidx.fragment.app.Fragment
import com.ok.enjoyer.application.helpers.ui.LayoutRes

fun Fragment.getLayoutRes(): LayoutRes {
    val annotation = this::class.java.annotations.find { it is LayoutRes } as? LayoutRes
    if (annotation != null) {
        return annotation
    } else {
        throw KotlinNullPointerException("Please add the LayoutRes annotation at the to of the class")
    }
}
