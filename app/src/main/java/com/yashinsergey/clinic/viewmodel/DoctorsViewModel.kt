package com.yashinsergey.clinic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yashinsergey.clinic.common.launchInScope
import com.yashinsergey.clinic.model.repos.DoctorsRepository
import com.yashinsergey.clinic.model.repos.network.json.Doctor

class DoctorsViewModel(private val repos: DoctorsRepository): ViewModel() {



    val doctorListResult = MutableLiveData<Result<List<Doctor>>>()

    fun getDoctorsList() = launchInScope({ repos.getDoctorList() }, doctorListResult)

}