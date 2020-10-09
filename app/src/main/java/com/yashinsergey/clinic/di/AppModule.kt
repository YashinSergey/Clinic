package com.yashinsergey.clinic.di

import com.yashinsergey.clinic.model.repos.DoctorsRepository
import com.yashinsergey.clinic.model.repos.DoctorsRepositoryImpl
import com.yashinsergey.clinic.model.repos.ReceptionRepository
import com.yashinsergey.clinic.model.repos.ReceptionRepositoryImpl
import com.yashinsergey.clinic.model.repos.network.api.DoctorsApi
import com.yashinsergey.clinic.model.repos.network.api.ReceptionApi
import com.yashinsergey.clinic.model.repos.network.retrofit.ClinicRetrofit
import com.yashinsergey.clinic.viewmodel.CalendarFragmentViewModel
import com.yashinsergey.clinic.viewmodel.DoctorsListFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    single { ClinicRetrofit.getRetrofit() }

    single { get<Retrofit>().create(DoctorsApi::class.java) }
    single { get<Retrofit>().create(ReceptionApi::class.java) }

    single<DoctorsRepository> { DoctorsRepositoryImpl(get()) }
    single<ReceptionRepository> { ReceptionRepositoryImpl(get()) }

    viewModel { DoctorsListFragmentViewModel(get()) }
    viewModel { CalendarFragmentViewModel(get()) }

}