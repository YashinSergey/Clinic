package com.yashinsergey.clinic.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.yashinsergey.clinic.R
import com.yashinsergey.clinic.common.FragmentExtensions
import io.reactivex.functions.Consumer

class MainActivity : AppCompatActivity(), FragmentExtensions {

    private val doctorsListFragment = DoctorsListFragment()
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
        display(doctorsListFragment)

        doctorsListFragment.click.subscribe(clickConsumer)
        doctorsListFragment.doctorSubject.subscribe(calendarFragment.doctorSubject)
    }

    private fun display(fragment: Fragment) {
        supportFragmentManager.display(R.id.main_container, fragment, FragmentTransaction.TRANSIT_NONE)
    }
}