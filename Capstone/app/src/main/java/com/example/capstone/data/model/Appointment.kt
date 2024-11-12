package com.example.capstone.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.Date

@Entity (tableName = "appointment")
data class Appointment (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "userID")
    var userID: Int,

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "time")
    var time: Time,

    @ColumnInfo(name = "location")
    var location: String,

    @ColumnInfo(name = "doctor")
    var doctor: String,
)