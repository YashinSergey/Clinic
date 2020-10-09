package com.yashinsergey.clinic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yashinsergey.clinic.common.launchInScope
import com.yashinsergey.clinic.model.repos.ReceptionRepository
import com.yashinsergey.clinic.model.repos.network.json.AppointmentDay

class CalendarFragmentViewModel(private val receptionRepos: ReceptionRepository): ViewModel() {

    val appointmentsTimesResult = MutableLiveData<Result<AppointmentDay>>()

    fun getAppointmentsTimes(doctorId: Int, date: String) {
        launchInScope({receptionRepos.getAppointmentTimeData(doctorId, date)}, appointmentsTimesResult)
    }
}