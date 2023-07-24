package com.example.taskorganizaer.domain

import android.app.Application
import com.example.taskorganizaer.data.daos.TaskDao
import com.example.taskorganizaer.data.database.TaskDatabase
import com.example.taskorganizaer.data.models.TaskModel
import kotlinx.coroutines.flow.Flow

class TaskRepository(application: Application) {
    private var taskDao: TaskDao

    init {
        val database = TaskDatabase.getInstance(application)
        taskDao = database.taskDao()
    }

    fun getAllTasksFromRoom(): Flow<List<TaskModel>> = taskDao.getAllTasks()
    fun getTaskByIdFromRoom(noteId: Int): Flow<TaskModel> = taskDao.getTaskById(noteId)
    suspend fun insertTaskToRoom(taskModel: TaskModel) = taskDao.insertTask(taskModel)
    suspend fun updateTaskInRoom(taskModel: TaskModel) = taskDao.updateTask(taskModel)
    suspend fun deleteTaskFromRoom(taskModel: TaskModel) = taskDao.deleteTask(taskModel)
}