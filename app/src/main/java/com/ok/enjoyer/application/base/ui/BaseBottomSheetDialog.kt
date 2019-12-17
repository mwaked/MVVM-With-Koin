package com.ok.enjoyer.application.base.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ok.enjoyer.application.extinsions.getLayoutRes

abstract class BaseBottomSheetDialog: BottomSheetDialogFragment() {

    lateinit var baseActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as BaseActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes().layout, container, false)
    }

    abstract fun bindView(savedInstanceState: Bundle?)

}