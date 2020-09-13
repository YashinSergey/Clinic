package com.yashinsergey.clinic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yashinsergey.clinic.databinding.FragmentCalendarBinding
import com.yashinsergey.clinic.ui.common.LazyContainer

class CalendarFragment: Fragment() {

    private val lazyContainer = LazyContainer<FragmentCalendarBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        lazyContainer.getValue(
            { FragmentCalendarBinding.inflate(inflater, container, false) },
            { initFragmentViews(it) }
        ).root


    private fun initFragmentViews(binding: FragmentCalendarBinding) {

    }
}