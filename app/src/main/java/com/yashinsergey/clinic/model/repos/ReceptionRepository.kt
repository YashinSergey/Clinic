package com.yashinsergey.clinic.model.repos

import com.yashinsergey.clinic.model.repos.network.json.AppointmentDay

interface ReceptionRepository {

    suspend fun getAppointmentTimeData(doctorId: Int, date: String): AppointmentDay
}