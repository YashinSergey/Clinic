package com.yashinsergey.clinic.common

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


enum class RussianTimeZone(val utc: Int) {

    KALT(2), MSK(3), SAMT(4), YEKT(5), OMST(6), KRAT(7),
    IRKT(8), YAKT(9), VLAT(10), MAGT(11), PETT(12);

    companion object {

        fun TimeZone.getOffsetHours(): Int = rawOffset / (1000 * 60 * 60)

        fun TimeZone.getMSK(): String {
            val m = getOffsetHours() - 3
            return if(m==0) MSK.name else "${MSK.name}${if(m < 0) "-" else "+"}${Math.abs(m)}"
        }

        fun getReadableTimeZone(): String {
            val timezone = TimeZone.getDefault()
            val offsetHours = timezone.getOffsetHours()
            val russianTime = values().find { offsetHours == it.utc }
            return russianTime?.name ?: timezone.getMSK()
        }

    }
}

@SuppressLint("SimpleDateFormat")
val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")

@SuppressLint("SimpleDateFormat")
val dateFormat2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ")

val dateFormat3 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.US)

@SuppressLint("SimpleDateFormat")
val displayDateFormat = SimpleDateFormat("dd.MM.yyyy").apply { timeZone = TimeZone.getDefault() }

@SuppressLint("SimpleDateFormat")
val displayDateFormat2 = SimpleDateFormat("dd.MM.yy").apply { timeZone = TimeZone.getDefault() }

@SuppressLint("SimpleDateFormat")
val displayDateFormat3 = SimpleDateFormat("yyyy-MM-dd").apply { timeZone = TimeZone.getDefault() }

@SuppressLint("SimpleDateFormat")
val displayTimeFormat = SimpleDateFormat("HH:mm").apply { timeZone = TimeZone.getDefault() }

@SuppressLint("SimpleDateFormat")
val displayDateFormatDayTime = SimpleDateFormat("dd.MM.yyyy / HH:mm").apply { timeZone = TimeZone.getDefault() }

@SuppressLint("SimpleDateFormat")
val dayMonthDisplayFormat = SimpleDateFormat("dd MMMM").apply { timeZone = TimeZone.getDefault() }

fun String.parseReadableDate(withTimeZone: Boolean, withDayTime: Boolean): String {
    val date = this.getDateOrNull(dateFormat) ?: this.getDateOrNull(dateFormat2)
    val readable = date?.getReadable(withDayTime)
    return when {
        readable == null -> this
        withTimeZone -> "$readable по ${RussianTimeZone.getReadableTimeZone()}"
        else -> readable
    }
}

fun Date.getReadableDateOrTime(today: Boolean): String =
    (if(today) displayTimeFormat else displayDateFormat2).format(this)


fun Date.getReadable(withDayTime: Boolean): String =
    (if(withDayTime) displayDateFormatDayTime else displayDateFormat).format(this)

fun String.getDateOrNull(dateFormat: DateFormat): Date? {
    return try {dateFormat.parse(this)} catch (e: Exception) {null}
}

fun String.toRFC822(): String = if(this.length == 29 && this[26] == ':')
    "${this.subSequence(0, 26)}${this.subSequence(27, 29)}" else this

fun Long.toStringDateTime(formatType: SimpleDateFormat): String {
    val date = Date(this)
    return formatType.format(date)
}