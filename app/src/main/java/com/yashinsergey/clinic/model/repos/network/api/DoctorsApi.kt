package com.yashinsergey.clinic.model.repos.network.api

import com.yashinsergey.clinic.model.repos.network.json.Doctor
import retrofit2.http.GET
import retrofit2.http.Path

interface DoctorsApi {

    @GET("/doctor")
    suspend fun getDoctorList(): List<Doctor>

    @GET("/doctor/branches/{branchId}")
    suspend fun getDoctorsListByBranch(@Path("branchId") branchId: Int): List<Doctor>
}