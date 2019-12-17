package com.ok.enjoyer.application.extinsions

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.text.Html
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ok.enjoyer.R

fun View.setVisibility(visible: Boolean) {
    if (visible) this.setVisible() else this.setGone()
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun setGone(vararg views: View) {
    views.map { it.setGone() }
}

fun View.toggleVisibility() {
    if (this.isShown) this.setGone() else this.setVisible()
}

fun View.setInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun setVisible(vararg views: View) {
    views.map { it.setVisible() }
}


fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun setInvisible(vararg views: View) {
    views.map { it.setInvisible() }
}

fun View.margin(left: Float? = null, top: Float? = null, right: Float? = null, bottom: Float? = null) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = dpToPx(this) }
        top?.run { topMargin = dpToPx(this) }
        right?.run { rightMargin = dpToPx(this) }
        bottom?.run { bottomMargin = dpToPx(this) }
    }
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)

fun Context.dpToPx(dp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

/*
 * Integer Extensions
 */
fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.heightWithRatio(width: Int, height: Int): Int {
    val h = (this.toFloat() * height.toFloat()) / width.toFloat()
    return h.toInt()
}

/*
 * change image background tint
 */
fun ImageView.changeImageTintColor(color: Int) {
    this.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this.context, color))
}




fun AppCompatTextView.setTextFromHtml(str: String) {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        (Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY))
    } else {
        Html.fromHtml(str)
    }
}

fun View.shake() {
    val shakeAnim = AnimationUtils.loadAnimation(this.context, R.anim.shake)
    startAnimation(shakeAnim)
}

fun View.popup() {
    val shakeAnim = AnimationUtils.loadAnimation(this.context, R.anim.pop_up)
    startAnimation(shakeAnim)
}

fun View.toggleClickable() {
    this.isClickable = !this.isClickable
}

fun View.setVisibleOrGone(flag: Boolean) {
    if (flag) this.setVisible() else this.setGone()
}



fun Fragment.hideKeyboard() {
    val v = view ?: return
    activity?.hideKeyboard(v)
}


fun Activity.hideKeyboard() {
    if (currentFocus == null) View(this) else currentFocus?.let { hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.setBackgroundTint(color: String) {
    val states = arrayOf(intArrayOf(android.R.attr.state_enabled))
    val colors = intArrayOf(Color.parseColor(color))
    val tintList = ColorStateList(states, colors)
    this.background.setTintList(tintList)
}

fun AppCompatEditText.clear() {
    if (text.toString().isNotEmpty()) setText("")
}

fun View.reverseShowHide() {
    val reverseFade = AnimationUtils.loadAnimation(this.context, R.anim.reverse_fade_animation)
    startAnimation(reverseFade)
}

fun View.setRaduisWithColor(color: Int, raduis: Int){
    val shape = GradientDrawable()
    shape.cornerRadius = raduis.toFloat()
    shape.setColor(color)
    this.background = shape
}

/*  start EditTextKtx */
fun EditText.placeCursorToEnd(){
    this.setSelection(this.text.length)
}

fun EditText.placeCursorToStart(){
    this.setSelection(0)
}

fun EditText.getString() = this.text.toString()

fun clearAllEditText(vararg views: EditText) {
    for (view in views)
        view.text = null
}

fun getResId(resName: String, c: Class<*>): Int {
    return try {
        val idField = c.getDeclaredField(resName)
        idField.getInt(idField)
    } catch (e: Exception) {
        e.printStackTrace()
        -1
    }
}

fun TextView.changeTextColor(res: Int){
    this.setTextColor(ContextCompat.getColor(context, res))
}

