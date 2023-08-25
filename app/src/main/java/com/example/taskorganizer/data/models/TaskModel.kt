package com.example.taskorganizer.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "taskModel")
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title:String,
    val notes:String,
    @ColumnInfo(defaultValue = "2013-08-07T02:15:36.840053")
    val dateCreated: LocalDateTime,
    val dateModified: LocalDateTime? = null,
    val dateCompleted: LocalDateTime? = null,
    val deadline: LocalDateTime? = null

)
