package com.example.capstone.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.capstone.data.model.Appointment
import com.example.capstone.data.repository.AppointmentRepository
import com.example.capstone.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DACViewModel(application: Application) : AndroidViewModel(application) {
    private val appointmentRepository = AppointmentRepository(application)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    // insert an appointment
    fun insertAppointment(appointment: Appointment) {
        mainScope.launch(Dispatchers.IO) {
            appointmentRepository.insertAppointment(appointment)
        }
    }

    // get all appointments for a user by their userID
    fun getAllAppointments(userID: String, onResult: (List<Appointment>) -> Unit) {
        mainScope.launch(Dispatchers.IO) {
            val appointments = appointmentRepository.getAllAppointments(userID)
            // return list of all the user's appointments
            onResult(appointments)
        }
    }

    // delete an appointment by ID
    fun deleteAppointmentById(appointmentID: Long) {
        mainScope.launch(Dispatchers.IO) {
            appointmentRepository.deleteAppointmentById(appointmentID)
        }
    }

    // get an appointment by ID
    fun getAppointmentById(appointmentID: Long, onResult: (Appointment?) -> Unit) {
        mainScope.launch(Dispatchers.IO) {
            val appointment = appointmentRepository.getAppointmentById(appointmentID)
            onResult(appointment)
        }
    }
}