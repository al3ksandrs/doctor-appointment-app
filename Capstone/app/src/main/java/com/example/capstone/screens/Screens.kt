package com.example.capstone.screens

sealed class Screens(val route: String) {
    object Login: Screens("login")
    object Register: Screens("register")
    object MyAppointments: Screens("myAppointments")
}