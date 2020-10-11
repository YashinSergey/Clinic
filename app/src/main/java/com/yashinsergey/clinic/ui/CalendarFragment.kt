package com.yashinsergey.clinic.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yashinsergey.clinic.R
import com.yashinsergey.clinic.common.showDecisionDialog
import com.yashinsergey.clinic.common.showOkDialog
import com.yashinsergey.clinic.databinding.FragmentCalendarBinding
import com.yashinsergey.clinic.model.repos.network.json.AppointmentDay
import com.yashinsergey.clinic.model.repos.network.json.Doctor
import com.yashinsergey.clinic.model.repos.network.json.Receptions
import com.yashinsergey.clinic.ui.common.LazyContainer
import com.yashinsergey.clinic.ui.views.AppointmentTimeItem
import com.yashinsergey.clinic.viewmodel.CalendarFragmentViewModel
import io.reactivex.functions.Consumer
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalendarFragment: Fragment() {

    private val calendarViewModel: CalendarFragmentViewModel by viewModel()

    private val lazyContainer = LazyContainer<FragmentCalendarBinding>()
    private val click = PublishSubject.create<ButtonId>()

    val doctorSubject = BehaviorSubject.create<Doctor>()
    val anAppointmentReserveRequest = PublishSubject.create<Int>()

    var doctor: Doctor? = null

    private val adapter : GroupAdapter<GroupieViewHolder> by lazy {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        groupAdapter.setHasStableIds(true)
        groupAdapter
    }

    private val doctorConsumer = Consumer<Doctor> { doctor = it }

    private val requestDialogConsumer = Consumer<Pair<Int, String>> {
        showDecisionDialog(
            requireActivity(),
            R.string.dialog_request_an_appointment_title,
            resources.getString(R.string.dialog_request_an_appointment_text).format(it.second),
            R.string.dialog_positive_button_text,
            R.string.dialog_cancel_button_text,
            { calendarViewModel.reserveAnAppointment(it.first) }
        )
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        lazyContainer.getValue(
            { FragmentCalendarBinding.inflate(inflater, container, false) },
            { initFragmentViews(it) }
        ).root

    @SuppressLint("CheckResult")
    private fun initFragmentViews(binding: FragmentCalendarBinding) {
        initCalendar(binding)
        binding.timeRecyclerView.layoutManager = GridLayoutManager(context, 4)
        binding.timeRecyclerView.adapter = adapter
        doctorSubject.subscribe(doctorConsumer)
        viewModelConnect(binding)
    }

    @SuppressLint("SimpleDateFormat")
    private fun initCalendar(binding: FragmentCalendarBinding) {
        binding.calendar.minDate = System.currentTimeMillis()
        binding.calendar.setOnDateChangeListener { view, year, month, day ->
            doctor?.let { doctor ->
                val stringDate = "$year-${month+1}-$day"
                calendarViewModel.getAppointmentsTimes(doctor.id, stringDate)
            }
        }
    }

    private fun viewModelConnect(binding: FragmentCalendarBinding) {
        calendarViewModel.appointmentsTimesResult.observe(viewLifecycleOwner, Observer {
            if (it.isSuccess) {
                it.getOrNull()?.let { data ->
                    appointmentDayConsumer.accept(Pair(data, binding))
                }
            } else {
                adapter.clear()
                binding.selectedDate.text = ""
            }
        })

        calendarViewModel.reserveAnAppointmentResult.observe(viewLifecycleOwner, Observer {
            if (it.isFailure) {
                //TODO Разобраться почему приходит 403
                showOkDialog(
                    requireActivity(),
                    R.string.an_appointment_request_failure_title,
                    R.string.an_appointment_request_failure_text,
                    )
            }
        })
    }

    private val appointmentDayConsumer = Consumer<Pair<AppointmentDay, FragmentCalendarBinding>> {
        adapter.clear()
        it.second.selectedDate.text = it.first.date
        adapter.addAll(createGroups(it.first.receptions))
    }

    private fun createGroups(receptions: List<Receptions>): List<AppointmentTimeItem> {
        val groups = mutableListOf<AppointmentTimeItem>()
        receptions.forEachIndexed { i, reception ->
            groups.add(AppointmentTimeItem(i.toLong(), reception, requestDialogConsumer))
        }
        return groups
    }
}