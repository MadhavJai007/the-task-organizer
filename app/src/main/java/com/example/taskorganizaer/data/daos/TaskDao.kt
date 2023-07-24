package com.example.taskorganizaer.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskorganizaer.data.models.TaskModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM taskModel ORDER BY id ASC")
    fun getAllTasks(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE id = :taskId")
    fun getTaskById(taskId: Int): Flow<TaskModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskModel: TaskModel)

    @Update
    suspend fun updateTask(taskModel: TaskModel)

    @Delete
    suspend fun deleteTask(taskModel: TaskModel)

}