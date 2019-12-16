package com.ok.enjoyer.application.extinsions

import android.content.pm.PackageManager

fun IntArray.hasAllPermissionsGranted(): Boolean {
    for (grantResult in this) {
        if (grantResult == PackageManager.PERMISSION_DENIED) {
            return false
        }
    }
    return true
}
