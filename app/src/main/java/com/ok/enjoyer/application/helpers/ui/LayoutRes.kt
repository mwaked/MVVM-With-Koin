package com.ok.enjoyer.application.helpers.ui

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class LayoutRes(val layout: Int = 0, val menu: Int = 0, val withBack: Boolean = false, val withHandleKeyboard: Boolean = false)
