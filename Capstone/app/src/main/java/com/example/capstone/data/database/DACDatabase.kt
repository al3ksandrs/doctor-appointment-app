package com.example.capstone.data.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.capstone.data.model.Appointment
import com.example.capstone.data.model.User
import com.example.capstone.data.utils.Converters

@Database(entities = [User::class, Appointment::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DACDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun appointmentDao(): AppointmentDao

    companion object {
        // Create singleton
        private const val DATABASE_NAME = "DAC_DATABASE"

        @Volatile
        private var INSTANCE: DACDatabase? = null

        fun getDatabase(context: Context): DACDatabase? {
            if (INSTANCE == null) {
                synchronized(DACDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = try {
                            Room.databaseBuilder(
                                context.applicationContext,
                                DACDatabase::class.java, DATABASE_NAME
                            )
                                .fallbackToDestructiveMigration()
                                .build()
                        } catch (e: Exception) {
                            Log.e("Fatal_DB_Creation", e.toString())
                            null
                        }
                    }
                }
            }
            return INSTANCE
        }
    }
}