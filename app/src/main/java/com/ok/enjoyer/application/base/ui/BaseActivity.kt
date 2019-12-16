package com.ok.enjoyer.application.base.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import android.view.ViewGroup
import com.ok.enjoyer.R
import com.ok.enjoyer.application.extinsions.getLayoutRes

abstract class BaseActivity : LocalizationActivity(){

    private val dialog: Dialog by lazy {
        Dialog(this).also {
            it.setCancelable(false)
            it.setContentView(R.layout.dialog_loading)
            it.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setDefaultLanguage(currentLanguage.language)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes().layout)
        initBack()
        bindView(savedInstanceState)
    }

    abstract fun bindView(savedInstanceState: Bundle?)

    private fun initBack() {
        if (getLayoutRes().withBack) {
//            ivBack.setOnClickListener { onBackPressed() }
        }
    }

    override fun setTitle(title: CharSequence?) {
        if (title != null) {
//            tvToolbarTitle.text = title
        }
        super.setTitle(title)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (getLayoutRes().menu != 0) {
            menuInflater.inflate(getLayoutRes().menu, menu)
            onMenuCreated(menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        onMenuItemClickListener(item, item.itemId)
        return super.onOptionsItemSelected(item)

    }

    open fun onMenuCreated(menu: Menu?) {

    }

    open fun onMenuItemClickListener(item: MenuItem?, id: Int) {

    }

    fun showProgress(message: Int) {
        dialog.findViewById<TextView>(R.id.tvLoadingMessage)?.text = getString(message)
        dialog.show()
    }

    fun hideProgress() {
        dialog.dismiss()
    }

    fun hideSoftKeyboard() {
        val inputMethodManager = this.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
                this.currentFocus?.windowToken, 0)
    }

    fun setupUI(view: View) {
        // Set up touch listener for non-text box profile to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { v, event ->
                hideSoftKeyboard()
                false
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun RecyclerView.hideKeyboardWithScrolled() {
        this.setOnTouchListener { v, _ ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
            false
        }
    }

    fun showKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun showKeyboardImplicit() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }

}