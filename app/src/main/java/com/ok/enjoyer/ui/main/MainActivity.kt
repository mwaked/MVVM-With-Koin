package com.ok.enjoyer.ui.main

import android.os.Bundle
import com.ok.enjoyer.R
import com.ok.enjoyer.application.base.ui.BaseActivity
import com.ok.enjoyer.application.helpers.ui.LayoutRes
import kotlinx.android.synthetic.main.activity_main.*

@LayoutRes(R.layout.activity_main)
class MainActivity : BaseActivity() {

    override fun bindView(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
    }

}
