package com.riadh.tutorial.util

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build

fun Context.getAppVersion(): String {
    return try {
        val packageInfo = packageManager.getPackageInfoCompat(packageName)
        packageInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        "Unknown"
    }
}

fun PackageManager.getPackageInfoCompat(packageName: String, flags: Int = 0): PackageInfo =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
    } else {
        @Suppress("DEPRECATION") getPackageInfo(packageName, flags)
    }