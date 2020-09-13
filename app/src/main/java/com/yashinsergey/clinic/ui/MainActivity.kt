package com.yashinsergey.clinic.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.yashinsergey.clinic.R
import com.yashinsergey.clinic.common.FragmentExtensions
import com.yashinsergey.clinic.common.logE
import com.yashinsergey.clinic.viewmodel.DoctorsViewModel
import io.reactivex.functions.Consumer
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), FragmentExtensions {

    private val viewModel: DoctorsViewModel by viewModel()

    private val doctorListFragment = DoctorListFragment()
    private val calendarFragment = CalendarFragment()

    private val clickConsumer = Consumer<ButtonId> { id ->
        when (id!!) {
            ButtonId.CALENDAR -> display(calendarFragment)
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getDoctorsList()

        viewModel.allDoctorListResult.observe(this, Observer {
            if (it.isSuccess) {
                it.getOrNull()?.let { data ->
                    doctorListFragment.doctorListSubject.onNext(data)
                    display(doctorListFragment)
                }

            } else {
                logE("${it.exceptionOrNull()?.message}")
            }
        })

        doctorListFragment.click.subscribe(clickConsumer)
    }

    private fun display(fragment: Fragment) {
        supportFragmentManager.display(R.id.main_container, fragment, FragmentTransaction.TRANSIT_NONE)
    }
}