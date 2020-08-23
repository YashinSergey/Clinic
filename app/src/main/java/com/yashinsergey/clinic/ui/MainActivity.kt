package com.yashinsergey.clinic.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.yashinsergey.clinic.R
import com.yashinsergey.clinic.common.logD
import com.yashinsergey.clinic.common.logE
import com.yashinsergey.clinic.viewmodel.DoctorsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: DoctorsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getDoctorsList()

        viewModel.doctorListResult.observe(this, Observer {
            if (it.isSuccess) {
                it.getOrNull()?.forEach { logD("$it") }
            } else {
                logE("${it.exceptionOrNull()?.message}")
            }
        })

    }
}