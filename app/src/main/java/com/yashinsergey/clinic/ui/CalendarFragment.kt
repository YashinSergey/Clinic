package com.yashinsergey.clinic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yashinsergey.clinic.common.logD
import com.yashinsergey.clinic.databinding.FragmentCalendarBinding
import com.yashinsergey.clinic.ui.common.LazyContainer
import io.reactivex.subjects.PublishSubject
import java.util.*

class CalendarFragment: Fragment() {

    private val lazyContainer = LazyContainer<FragmentCalendarBinding>()
    private val click = PublishSubject.create<ButtonId>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        lazyContainer.getValue(
            { FragmentCalendarBinding.inflate(inflater, container, false) },
            { initFragmentViews(it) }
        ).root


    private fun initFragmentViews(binding: FragmentCalendarBinding) {
        binding.calendar.minDate = System.currentTimeMillis()
        val currentTime = Calendar.getInstance().time
        logD("currentTime: $currentTime")
        binding.calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            logD("date: ${view.date}")
            logD("formatted date: ${month+1}/$dayOfMonth/$year")

        }
    }
}