package com.example.capstone.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.capstone.data.model.Appointment

@Dao
interface AppointmentDao {
    @Insert
    suspend fun insertAppointment(appointment: Appointment)

    @Query("SELECT * FROM appointment WHERE userID = :userID")
    suspend fun getAllAppointments(userID: Long): List<Appointment>

    @Query("DELETE FROM appointment WHERE id = :appointmentID")
    suspend fun deleteAppointmentById(appointmentID: Long)

    @Query("SELECT * FROM appointment WHERE id = :appointmentID")
    suspend fun getAppointmentById(appointmentID: Long): Appointment?
}