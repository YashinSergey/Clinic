package com.yashinsergey.clinic.model.repos.network.retrofit

import com.yashinsergey.clinic.model.repos.network.api.DoctorsApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class ClinicRetrofit {



    companion object {
        private val BASE_URL = "https://petclinic-app.herokuapp.com"

        fun getRetrofit() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}