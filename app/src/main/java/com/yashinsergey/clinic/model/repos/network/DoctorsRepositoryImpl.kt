package com.yashinsergey.clinic.model.repos.network

import com.yashinsergey.clinic.model.repos.DoctorsRepository
import com.yashinsergey.clinic.model.repos.network.api.DoctorsApi
import com.yashinsergey.clinic.model.repos.network.json.Doctor

class DoctorsRepositoryImpl(private val api: DoctorsApi): DoctorsRepository {

    override suspend fun getDoctorList(): List<Doctor> = api.getDoctorList()

    override suspend fun getDoctorListByBranch(branchId: Int): List<Doctor> = api.getDoctorsListByBranch(branchId)
}