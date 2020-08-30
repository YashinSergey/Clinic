package com.yashinsergey.clinic.common

import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.yashinsergey.clinic.BuildConfig

fun loadImage(picUrl: String, view: ImageView) {
    Picasso.get().load(picUrl).into(view)
}

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