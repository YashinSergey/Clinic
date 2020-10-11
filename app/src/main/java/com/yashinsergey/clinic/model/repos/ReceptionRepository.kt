package com.yashinsergey.clinic.model.repos

import com.yashinsergey.clinic.model.repos.network.json.AppointmentDay
import okhttp3.ResponseBody

interface ReceptionRepository {

    suspend fun getAppointmentTimeData(doctorId: Int, date: String): AppointmentDay

    suspend fun reserveAnAppointment(receptionId: Int): ResponseBody
}