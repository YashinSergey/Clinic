package com.yashinsergey.clinic.model.repos.network

import com.yashinsergey.clinic.model.repos.DoctorsRepository
import com.yashinsergey.clinic.model.repos.network.api.DoctorsApi
import com.yashinsergey.clinic.model.repos.network.json.Doctor

class DoctorsRepositoryImpl(private val api: DoctorsApi): DoctorsRepository {

    override suspend fun getDoctorList(): List<Doctor> =
//        api.getDoctorList()
    doctorsList
}

val doctorsList = listOf(
    Doctor(43, "doctor", 20, "Doctor1", "surgeon", "Doc1", "position name"),
    Doctor(34, "doctor", 14, "Doctor2", "surgeon", "Doc2", "position name"),
    Doctor(45, "doctor", 25, "Doctor3", "surgeon", "Doc3", "position name"),
    Doctor(22, "doctor", 2, "Doctor4", "surgeon", "Doc4", "position name"),
    Doctor(55, "doctor", 35, "Doctor5", "surgeon", "Doc5", "position name"),
    Doctor(26, "doctor", 6, "Doctor6", "surgeon", "Doc6", "position name"),
    Doctor(24, "doctor", 4, "Doctor7", "surgeon", "Doc7", "position name"),
    Doctor(33, "doctor", 13, "Doctor8", "surgeon", "Doc8", "position name"),
)