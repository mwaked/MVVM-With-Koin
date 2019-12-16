package com.ok.enjoyer.application.base.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.ok.enjoyer.application.extinsions.getLayoutRes

abstract class BaseFragment : Fragment(){

    lateinit var baseActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as BaseActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(getLayoutRes().menu != 0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes().layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (getLayoutRes().menu != 0) {
            inflater.inflate(getLayoutRes().menu, menu)
            onMenuCreated(menu)
        }
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onMenuItemClickListener(item, item!!.itemId)
        return super.onOptionsItemSelected(item)

    }

    abstract fun bindView(savedInstanceState: Bundle?)


    open fun onMenuCreated(menu: Menu?) {

    }

    open fun onMenuItemClickListener(item: MenuItem?, id: Int) {

    }

    override fun onDestroy() {
        closeKeyboard(this.view?.rootView?.findFocus())
        super.onDestroy()
    }

    fun showKeyboard() {
        val inputMethodManager = this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }


    fun closeKeyboard() {
        val inputMethodManager = this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    fun closeKeyboard(view: View?) {
        val imm = this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun hideSoftKeyboard() {
        val inputMethodManager = activity?.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
                activity?.currentFocus?.windowToken, 0)
    }

    fun setupUI(view: View) {
        // Set up touch listener for non-text box profile to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
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

}

