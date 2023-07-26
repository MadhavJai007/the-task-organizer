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

    fun getAllTasksByIDAsc(): Flow<List<TaskModel>> = taskDao.getAllTasks()
    fun getTaskByIdFromRoom(noteId: Int): Flow<TaskModel> = taskDao.getTaskById(noteId)


    // sorting operations

    fun sortTasksByIDDesc(): Flow<List<TaskModel>> = taskDao.sortTasksByIDDesc()

    fun sortTasksByAlphaAsc(): Flow<List<TaskModel>> = taskDao.sortTasksByAlphaAsc()
    fun sortTasksByAlphaDesc(): Flow<List<TaskModel>> = taskDao.sortTasksByAlphaDesc()


    // CRUD operations
    suspend fun insertTaskToRoom(taskModel: TaskModel) = taskDao.insertTask(taskModel)
    suspend fun updateTaskInRoom(taskModel: TaskModel) = taskDao.updateTask(taskModel)
    suspend fun deleteTaskFromRoom(taskModel: TaskModel) = taskDao.deleteTask(taskModel)
    suspend fun upsertTaskInRoom(taskModel: TaskModel) = taskDao.upsertTask(taskModel)


}