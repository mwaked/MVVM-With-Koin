package com.ok.enjoyer.application.extinsions

import android.app.Activity
import android.app.ActivityManager
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.util.Log
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.os.ConfigurationCompat
import androidx.fragment.app.FragmentActivity
import com.ok.enjoyer.application.helpers.ui.LayoutRes


fun Activity.getLayoutRes(): LayoutRes {
    val annotation = this::class.java.annotations.find { it is LayoutRes } as? LayoutRes
    if (annotation != null) {
        return annotation
    } else {
        throw KotlinNullPointerException("Please add the LayoutRes annotation at the top of the class")
    }
}

inline fun <reified T: Any> Activity.startActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T: Any> Context.startActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T: Any> Context.startActivityWithClearTask() {
    startActivity(Intent(this, T::class.java).addClearFlags())
}

inline fun <reified T: Any> FragmentActivity.startActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T: Any> Activity.startActivity(data: Intent) {
    startActivity(Intent(this, T::class.java).putExtras(data))
}

inline fun <reified T: Any> Context.startActivity(data: Intent) {
    startActivity(Intent(this, T::class.java).putExtras(data))
}

inline fun <reified T: Any> Context.startActivityWithClearTask(data: Intent) {
    startActivity(Intent(this, T::class.java).putExtras(data).addClearFlags())
}

inline fun <reified T: Any> Activity.startActivityForResult(requestCode: Int) {
    startActivityForResult(Intent(this, T::class.java), requestCode)
}

inline fun <reified T: Any> Activity.startActivityForResult(data: Intent, requestCode: Int) {
    startActivityForResult(Intent(this, T::class.java).putExtras(data), requestCode)
}

fun Activity.isPermissionsGranted(vararg permissions: String): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true
    return permissions.all { checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }
}

fun Activity.isPermissionsGranted(vararg permissions: String, code: Int): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true
    if (permissions.all { checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }) return true
    else requestPermissions(permissions, code)
    return false
}

fun Activity.isNetworkConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
    } else {

        try {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                Log.i("update_statut", "Network is available : true")
                return true
            }
        } catch (e: Exception) {
            Log.i("update_statut", "" + e.message)
        }
    }
    return false
}

fun Intent.addClearFlags() = addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)

fun Context.clearAllNotification(){
    val nMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
    nMgr!!.cancelAll()
}

fun Context.getCountryRegionFromPhone(): String {
    val service = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    var code: String?

    code = service.networkCountryIso

    if(code.isNullOrEmpty()){
        code = service.simCountryIso
    }

    if(code.isNullOrEmpty()){
        val localListCompat = ConfigurationCompat.getLocales(resources.configuration)
        code = localListCompat.get(0).country
    }

    return code?.toUpperCase() ?: ""

}

fun Context.isAppOnForeground(): Boolean {
    val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val appProcesses = activityManager.runningAppProcesses ?: return false
    for (appProcess in appProcesses) {
        if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName == packageName) {
            return true
        }
    }
    return false
}
 fun Context.getScreenWidth(): Int {
    val widthPixels = Resources.getSystem().displayMetrics.widthPixels
    return widthPixels / (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}




