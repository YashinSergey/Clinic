package com.yashinsergey.clinic.common

import android.app.Activity
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog

fun showDecisionDialog(activity: Activity,
                       @StringRes titleId: Int?,
                       text : String,
                       @StringRes positiveTextId : Int,
                       @StringRes cancelTextId : Int,
                       f: () -> Unit) {
    with(AlertDialog.Builder(activity)) {
        titleId?.let { setTitle(titleId) }
        setMessage(text)
        setPositiveButton(positiveTextId, { _, _ -> f.invoke() })
        setNeutralButton(cancelTextId, { dialog, _ -> dialog.dismiss() })
        create().show()
    }
}