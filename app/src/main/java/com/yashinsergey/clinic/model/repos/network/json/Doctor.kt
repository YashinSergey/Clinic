package com.yashinsergey.clinic.model.repos.network.json

data class Doctor (
    val id: Int,
    val age: Int,
    val degree: String,
    val experienceYears: Int,
    val name: String,
    val specialization: String,
    val surname: String,
    val photoUrl: String
)