package com.example.capstone.data.repository

import android.content.Context
import com.example.capstone.data.database.AppointmentDao
import com.example.capstone.data.database.DACDatabase
import com.example.capstone.data.model.Appointment

class AppointmentRepository(context: Context) {
    private val appointmentDao: AppointmentDao

    // initialize database
    init {
        val database = DACDatabase.getDatabase(context)
        appointmentDao = database!!.appointmentDao()
    }

    // insert a new appointment
    suspend fun insertAppointment(appointment: Appointment) {
        appointmentDao.insertAppointment(appointment)
    }

    // get all appointments for a user by userID
    suspend fun getAllAppointments(userID: Long): List<Appointment> {
        return appointmentDao.getAllAppointments(userID)
    }

    // delete a appointment by its ID
    suspend fun deleteAppointmentById(appointmentID: Long) {
        appointmentDao.deleteAppointmentById(appointmentID)
    }

    // get a appointment by its ID
    suspend fun getAppointmentById(appointmentID: Long): Appointment? {
        return appointmentDao.getAppointmentById(appointmentID)
    }
}