package com.yashinsergey.clinic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yashinsergey.clinic.common.launchInScope
import com.yashinsergey.clinic.model.repos.DoctorsRepository
import com.yashinsergey.clinic.model.repos.network.json.Doctor

class DoctorsViewModel(private val repos: DoctorsRepository): ViewModel() {



    val allDoctorListResult = MutableLiveData<Result<List<Doctor>>>()
    val doctorsByBranchResult = MutableLiveData<Result<List<Doctor>>>()

    fun getDoctorsList() = launchInScope({ repos.getDoctorList() }, allDoctorListResult)

    fun getDoctorListByBranch(branchId: Int) = launchInScope({repos.getDoctorListByBranch(branchId)}, doctorsByBranchResult)

}