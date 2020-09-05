package com.yashinsergey.clinic.model.repos.network.json

data class Doctor (
    val id : Int,
    val age : Int,
    val branch : Branch,
    val experienceYears : Int,
    val name : String,
    val photoUrl : String,
    val position : String,
    val specialization : String,
    val surname : String
)