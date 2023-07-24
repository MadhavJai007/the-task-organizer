package com.example.taskorganizaer.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskModel")
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title:String,
    var notes:String
)
