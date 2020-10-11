package com.yashinsergey.clinic.model.repos.network.api

import com.yashinsergey.clinic.model.repos.network.json.AppointmentDay
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ReceptionApi {
    @GET("/reception/{doctorId}/schedule/date")
    suspend fun getAppointmentTimeData(
        @Path("doctorId") doctorId: Int,
        @Query("date") date: String
    ): AppointmentDay

    @PUT("/reception/{receptionId}")
    suspend fun reserveAnAppointment(
        @Path("receptionId") receptionId: Int
    ): ResponseBody
}