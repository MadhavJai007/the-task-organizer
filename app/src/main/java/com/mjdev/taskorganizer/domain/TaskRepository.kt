package com.mjdev.taskorganizer.domain

import android.app.Application
import com.mjdev.taskorganizer.data.daos.TaskDao
import com.mjdev.taskorganizer.data.database.TaskDatabase
import com.mjdev.taskorganizer.data.models.TaskModel
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

    fun sortTasksByAlphaAscWithCompletedFilter(): Flow<List<TaskModel>> = taskDao.sortTasksByAlphaAscWithCompletedFilter()

    fun sortTasksByAlphaAscWithIncompletedFilter(): Flow<List<TaskModel>> = taskDao.sortTasksByAlphaAscWithIncompletedFilter()

    fun sortTasksByAlphaAscWithDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByAlphaAscWithDeadlineFilter()

    fun sortTasksByAlphaAscWithNoDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByAlphaDescWithNoDeadlineFilter()


    fun sortTasksByAlphaDesc(): Flow<List<TaskModel>> = taskDao.sortTasksByAlphaDesc()

    fun sortTasksByAlphaDescWithCompletedFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByAlphaDescWithCompletedFilter()

    fun sortTasksByAlphaDescWithIncompletedFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByAlphaDescWithIncompletedFilter()

    fun sortTasksByAlphaDescWithDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByAlphaDescWithDeadlineFilter()

    fun sortTasksByAlphaDescWithNoDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByAlphaDescWithNoDeadlineFilter()

    fun sortTasksByDateCreatedAsc(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCreatedAsc()

    fun sortTasksByDateCreatedAscWithCompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCreatedAscWithCompleteFilter()

    fun sortTasksByDateCreatedAscWithIncompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCreatedAscWithIncompleteFilter()

    fun sortTasksByDateCreatedAscWithDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCreatedAscWithDeadlineFilter()

    fun sortTasksByDateCreatedAscWithNoDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCreatedAscWithNoDeadlineFilter()

    fun sortTasksByDateCreatedDesc(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCreatedDesc()

    fun sortTasksByDateCreatedDescWithCompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCreatedDescWithCompleteFilter()


    fun sortTasksByDateCreatedDescWithIncompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCreatedDescWithIncompleteFilter()

    fun sortTasksByDateCreatedDescWithDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCreatedDescWithDeadlineFilter()

    fun sortTasksByDateCreatedDescWithNoDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCreatedDescWithNoDeadlineFilter()

    fun sortTasksByDateModifiedAsc(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateModifiedAsc()

    fun sortTasksByDateModifiedAscWithCompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateModifiedAscWithCompleteFilter()

    fun sortTasksByDateModifiedAscWithIncompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateModifiedAscWithIncompleteFilter()

    fun sortTasksByDateModifiedAscWithDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateModifiedAscWithDeadlineFilter()

    fun sortTasksByDateModifiedAscWithNoDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateModifiedAscWithNoDeadlineFilter()


    fun sortTasksByDateModifiedDesc(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateModifiedDesc()

    fun sortTasksByDateModifiedDescWithCompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateModifiedDescWithCompleteFilter()

    fun sortTasksByDateModifiedDescWithIncompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateModifiedDescWithIncompleteFilter()

    fun sortTasksByDateModifiedDescWithDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateModifiedDescWithDeadlineFilter()

    fun sortTasksByDateModifiedDescWithNoDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateModifiedDescWithNoDeadlineFilter()


    fun sortTasksByDateCompletedAsc(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCompletedAsc()

    fun sortTasksByDateCompletedAscWithCompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCompletedAscWithCompleteFilter()

    fun sortTasksByDateCompletedAscWithIncompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCompletedAscWithIncompleteFilter()

    fun sortTasksByDateCompletedAscWithDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCompletedAscWithDeadlineFilter()

    fun sortTasksByDateCompletedAscWithNoDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCompletedAscWithNoDeadlineFilter()

    fun sortTasksByDateCompletedDesc(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCompletedDesc()

    fun sortTasksByDateCompletedDescWithCompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCompletedDescWithCompleteFilter()

    fun sortTasksByDateCompletedDescWithIncompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCompletedDescWithIncompleteFilter()

    fun sortTasksByDateCompletedDescWithDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCompletedDescWithDeadlineFilter()

    fun sortTasksByDateCompletedDescWithNoDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDateCompletedDescWithNoDeadlineFilter()

    fun sortTasksByDeadlineAsc(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDeadlineAsc()

    fun sortTasksByDeadlineAscWithCompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDeadlineAscWithCompleteFilter()

    fun sortTasksByDeadlineAscWithIncompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDeadlineAscWithIncompleteFilter()

    fun sortTasksByDeadlineAscWithDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDeadlineAscWithDeadlineFilter()

    fun sortTasksByDeadlineAscWithNoDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDeadlineAscWithNoDeadlineFilter()

    fun sortTasksByDeadlineDesc(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDeadlineDesc()

    fun sortTasksByDeadlineDescWithCompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDeadlineDescWithCompleteFilter()

    fun sortTasksByDeadlineDescWithIncompleteFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDeadlineDescWithIncompleteFilter()

    fun sortTasksByDeadlineDescWithDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDeadlineDescWithDeadlineFilter()

    fun sortTasksByDeadlineDescWithNoDeadlineFilter(): Flow<List<TaskModel>> =
        taskDao.sortTasksByDeadlineDescWithNoDeadlineFilter()

    // CRUD operations
    suspend fun insertTaskToRoom(taskModel: TaskModel) = taskDao.insertTask(taskModel)
    suspend fun updateTaskInRoom(taskModel: TaskModel) = taskDao.updateTask(taskModel)
    suspend fun deleteTaskFromRoom(taskModel: TaskModel) = taskDao.deleteTask(taskModel)
    suspend fun upsertTaskInRoom(taskModel: TaskModel) = taskDao.upsertTask(taskModel)


}