package com.yashinsergey.clinic.model.repos

import com.yashinsergey.clinic.model.repos.network.api.ReceptionApi

class ReceptionRepositoryImpl(private val api: ReceptionApi) : ReceptionRepository {

    override suspend fun getAppointmentTimeData(doctorId: Int, date: String) =
        api.getAppointmentTimeData(doctorId, date)

}