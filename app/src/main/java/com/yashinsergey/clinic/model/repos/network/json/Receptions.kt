package com.yashinsergey.clinic.model.repos.network.json

import com.yashinsergey.clinic.common.*

data class Receptions(
    val beginTime: String,
    val endTime: String,
    val id: Int,
    val ocupied: Boolean
) {
    fun getTime(date: String): String? = dateFormat3.parse(date)?.getReadableDateOrTime(true)
}