package com.yashinsergey.clinic.model.repos

import com.yashinsergey.clinic.model.repos.network.json.Doctor
import retrofit2.http.Path

interface DoctorsRepository {

    suspend fun getDoctorList(): List<Doctor>

    suspend fun getDoctorListByBranch(@Path("branchId") branchId: Int): List<Doctor>
}