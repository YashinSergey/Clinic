package com.yashinsergey.clinic.model.repos

import com.yashinsergey.clinic.model.repos.network.json.Doctor

interface DoctorsRepository {
    suspend fun getDoctorList(): List<Doctor>
}