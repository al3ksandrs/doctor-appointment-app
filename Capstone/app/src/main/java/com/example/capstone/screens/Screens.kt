package com.example.capstone.screens

sealed class Screens(val route: String) {
    object Login: Screens("login")
    object Register: Screens("register")
    object MyAppointments: Screens("myAppointments")
    object CancelAppointment: Screens("cancelAppointment")
    object ChangeTimeslot: Screens("changeTimeslot")
    object CalendarView: Screens("calendarView")
    object TimeslotSelect: Screens("timeslotSelect")
    object TimeslotDetails: Screens("timeslotDetails")
    object AppointmentDetails: Screens("appointmentDetails")
}