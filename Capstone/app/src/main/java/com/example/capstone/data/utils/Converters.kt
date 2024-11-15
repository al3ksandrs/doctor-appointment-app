package com.example.capstone.data.utils

import androidx.room.TypeConverter
import java.sql.Time
import java.util.Date

class Converters {
    @TypeConverter
    fun fromDate(value: Date?): String? {
        return value?.let { Utils.dateFormatter.format(it) }
    }

    @TypeConverter
    fun toDate(value: String?): Date? {
        return value?.let { Utils.dateFormatter.parse(it) }
    }

    @TypeConverter
    fun fromTime(value: Time?): String? {
        return value?.let { Utils.timeFormatter.format(it) }
    }

    @TypeConverter
    fun toTime(value: String?): Time? {
        return value?.let { Time(Utils.timeFormatter.parse(it)?.time ?: 0L) }
    }
}