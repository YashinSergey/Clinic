package com.yashinsergey.clinic.model.repos.network.json

data class AppointmentDay(
    val date : String,
    val doctor : Doctor,
    val receptions : List<Receptions>
)