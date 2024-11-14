package com.example.capstone.data.model

data class Notification(
    val appointmentId: Long,
    val hoursBefore: Int,
    val message: String
)