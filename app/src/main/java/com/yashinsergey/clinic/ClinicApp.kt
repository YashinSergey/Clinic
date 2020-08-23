package com.yashinsergey.clinic

import android.app.Application
import com.yashinsergey.clinic.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ClinicApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ClinicApp)
            modules(appModule)
        }
    }
}