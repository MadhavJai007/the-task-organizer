package com.example.taskorganizer.data.database

import androidx.room.TypeConverter
import java.time.LocalDateTime

open class TypeConverter {
    @TypeConverter
    fun toDate(date: String?): LocalDateTime? {
        return date?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun fromDate(date: LocalDateTime?): String? {
        return date?.toString()
    }

}