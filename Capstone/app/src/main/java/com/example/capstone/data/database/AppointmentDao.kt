package com.example.capstone.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.capstone.data.model.Appointment
import com.example.capstone.data.model.User

@Dao
interface AppointmentDao {
    @Insert
    suspend fun insertAppointment(appointment: Appointment)

    @Query("SELECT * FROM Appointment WHERE userID = :userID")
    suspend fun getAllAppointments(userID: Long): List<Appointment>

    @Query("DELETE FROM Appointment WHERE id = :appointmentID")
    suspend fun deleteAppointmentById(appointmentID: Long)
}