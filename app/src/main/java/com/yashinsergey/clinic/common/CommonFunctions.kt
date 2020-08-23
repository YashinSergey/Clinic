package com.yashinsergey.clinic.common

import android.util.Log
import com.yashinsergey.clinic.BuildConfig

fun logD(message: String, tag: String = "CLINIC_APP") {
    if (BuildConfig.DEBUG) {
        Log.d(tag, message)
    }
}

fun logE(message: String, tag: String = "CLINIC_APP") {
    if (BuildConfig.DEBUG) {
        Log.e(tag, message)
    }
}