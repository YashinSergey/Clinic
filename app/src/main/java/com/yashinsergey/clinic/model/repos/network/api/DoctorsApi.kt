package com.yashinsergey.clinic.model.repos.network.api

import com.yashinsergey.clinic.model.repos.network.json.Doctor
import retrofit2.http.GET

interface DoctorsApi {

    @GET("/doctor")
    suspend fun getDoctorList(): List<Doctor>
}