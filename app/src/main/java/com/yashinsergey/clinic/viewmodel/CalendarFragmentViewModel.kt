package com.yashinsergey.clinic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yashinsergey.clinic.common.launchInScope
import com.yashinsergey.clinic.model.repos.ReceptionRepository
import com.yashinsergey.clinic.model.repos.network.json.AppointmentDay
import okhttp3.ResponseBody

class CalendarFragmentViewModel(private val receptionRepos: ReceptionRepository): ViewModel() {

    val appointmentsTimesResult = MutableLiveData<Result<AppointmentDay>>()
    val reserveAnAppointmentResult = MutableLiveData<Result<ResponseBody>>()

    fun getAppointmentsTimes(doctorId: Int, date: String) =
        launchInScope(
            { receptionRepos.getAppointmentTimeData(doctorId, date) },
            appointmentsTimesResult
        )

    fun reserveAnAppointment(receptionId: Int) =
        launchInScope(
            { receptionRepos.reserveAnAppointment(receptionId) },
            reserveAnAppointmentResult
        )
}