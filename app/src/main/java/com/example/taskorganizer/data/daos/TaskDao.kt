package com.example.taskorganizer.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.taskorganizer.data.models.TaskModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM taskModel ORDER BY id ASC")
    fun getAllTasks(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE id = :taskId")
    fun getTaskById(taskId: Int): Flow<TaskModel>


    // CRUD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskModel: TaskModel)

    @Update
    suspend fun updateTask(taskModel: TaskModel)

    @Delete
    suspend fun deleteTask(taskModel: TaskModel)

    @Upsert
    suspend fun upsertTask(taskModel: TaskModel)


    // sort / filter
    @Query("SELECT * FROM taskModel ORDER BY id DESC")
    fun sortTasksByIDDesc(): Flow<List<TaskModel>>


    @Query("SELECT * FROM taskModel ORDER BY title ASC ")
    fun sortTasksByAlphaAsc(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NOT NULL ORDER BY title ASC ")
    fun sortTasksByAlphaAscWithCompletedFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NULL ORDER BY title ASC ")
    fun sortTasksByAlphaAscWithIncompletedFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE deadline IS NOT NULL ORDER BY title ASC ")
    fun sortTasksByAlphaAscWithDeadlineFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE deadline IS NULL ORDER BY title ASC ")
    fun sortTasksByAlphaAscWithNoDeadlineFilter(): Flow<List<TaskModel>>


    @Query("SELECT * FROM taskModel ORDER BY title DESC")
    fun sortTasksByAlphaDesc(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NOT NULL ORDER BY title DESC ")
    fun sortTasksByAlphaDescWithCompletedFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NULL ORDER BY title DESC ")
    fun sortTasksByAlphaDescWithIncompletedFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE deadline IS NOT NULL ORDER BY title DESC ")
    fun sortTasksByAlphaDescWithDeadlineFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE deadline IS NULL ORDER BY title DESC ")
    fun sortTasksByAlphaDescWithNoDeadlineFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel ORDER BY dateCreated ASC")
    fun sortTasksByDateCreatedAsc(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NOT NULL ORDER BY dateCreated ASC")
    fun sortTasksByDateCreatedAscWithCompleteFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NULL ORDER BY dateCreated ASC")
    fun sortTasksByDateCreatedAscWithIncompleteFilter(): Flow<List<TaskModel>>


    @Query("SELECT * FROM taskModel WHERE deadline IS NOT NULL ORDER BY dateCreated ASC")
    fun sortTasksByDateCreatedAscWithDeadlineFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE deadline IS NULL ORDER BY dateCreated ASC")
    fun sortTasksByDateCreatedAscWithNoDeadlineFilter(): Flow<List<TaskModel>>


    @Query("SELECT * FROM taskModel ORDER BY dateCreated DESC")
    fun sortTasksByDateCreatedDesc(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NOT NULL ORDER BY dateCreated DESC")
    fun sortTasksByDateCreatedDescWithCompleteFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NULL ORDER BY dateCreated DESC")
    fun sortTasksByDateCreatedDescWithIncompleteFilter(): Flow<List<TaskModel>>


    @Query("SELECT * FROM taskModel WHERE deadline IS NOT NULL ORDER BY dateCreated DESC")
    fun sortTasksByDateCreatedDescWithDeadlineFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE deadline IS NULL ORDER BY dateCreated DESC")
    fun sortTasksByDateCreatedDescWithNoDeadlineFilter(): Flow<List<TaskModel>>





    @Query("SELECT * FROM taskModel ORDER BY dateModified ASC")
    fun sortTasksByDateModifiedAsc(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NOT NULL ORDER BY dateModified ASC")
    fun sortTasksByDateModifiedAscWithCompleteFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NULL ORDER BY dateModified ASC")
    fun sortTasksByDateModifiedAscWithIncompleteFilter(): Flow<List<TaskModel>>


    @Query("SELECT * FROM taskModel WHERE deadline IS NOT NULL ORDER BY dateModified ASC")
    fun sortTasksByDateModifiedAscWithDeadlineFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE deadline IS NULL ORDER BY dateModified ASC")
    fun sortTasksByDateModifiedAscWithNoDeadlineFilter(): Flow<List<TaskModel>>


    @Query("SELECT * FROM taskModel ORDER BY dateModified DESC")
    fun sortTasksByDateModifiedDesc(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NOT NULL ORDER BY dateModified DESC")
    fun sortTasksByDateModifiedDescWithCompleteFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NULL ORDER BY dateModified DESC")
    fun sortTasksByDateModifiedDescWithIncompleteFilter(): Flow<List<TaskModel>>


    @Query("SELECT * FROM taskModel WHERE deadline IS NOT NULL ORDER BY dateModified DESC")
    fun sortTasksByDateModifiedDescWithDeadlineFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE deadline IS NULL ORDER BY dateModified DESC")
    fun sortTasksByDateModifiedDescWithNoDeadlineFilter(): Flow<List<TaskModel>>







    @Query("SELECT * FROM taskModel ORDER BY dateCompleted ASC")
    fun sortTasksByDateCompletedAsc(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NOT NULL ORDER BY dateCompleted ASC")
    fun sortTasksByDateCompletedAscWithCompleteFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NULL ORDER BY dateCompleted ASC")
    fun sortTasksByDateCompletedAscWithIncompleteFilter(): Flow<List<TaskModel>>


    @Query("SELECT * FROM taskModel WHERE deadline IS NOT NULL ORDER BY dateCompleted ASC")
    fun sortTasksByDateCompletedAscWithDeadlineFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE deadline IS NULL ORDER BY dateCompleted ASC")
    fun sortTasksByDateCompletedAscWithNoDeadlineFilter(): Flow<List<TaskModel>>


    @Query("SELECT * FROM taskModel ORDER BY dateCompleted DESC")
    fun sortTasksByDateCompletedDesc(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NOT NULL ORDER BY dateCompleted DESC")
    fun sortTasksByDateCompletedDescWithCompleteFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NULL ORDER BY dateCompleted DESC")
    fun sortTasksByDateCompletedDescWithIncompleteFilter(): Flow<List<TaskModel>>


    @Query("SELECT * FROM taskModel WHERE deadline IS NOT NULL ORDER BY dateCompleted DESC")
    fun sortTasksByDateCompletedDescWithDeadlineFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE deadline IS NULL ORDER BY dateCompleted DESC")
    fun sortTasksByDateCompletedDescWithNoDeadlineFilter(): Flow<List<TaskModel>>





    @Query("SELECT * FROM taskModel ORDER BY deadline ASC")
    fun sortTasksByDeadlineAsc(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NOT NULL ORDER BY deadline ASC")
    fun sortTasksByDeadlineAscWithCompleteFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NULL ORDER BY deadline ASC")
    fun sortTasksByDeadlineAscWithIncompleteFilter(): Flow<List<TaskModel>>


    @Query("SELECT * FROM taskModel WHERE deadline IS NOT NULL ORDER BY deadline ASC")
    fun sortTasksByDeadlineAscWithDeadlineFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE deadline IS NULL ORDER BY deadline ASC")
    fun sortTasksByDeadlineAscWithNoDeadlineFilter(): Flow<List<TaskModel>>


    @Query("SELECT * FROM taskModel ORDER BY deadline DESC")
    fun sortTasksByDeadlineDesc(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NOT NULL ORDER BY deadline DESC")
    fun sortTasksByDeadlineDescWithCompleteFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE dateCompleted IS NULL ORDER BY deadline DESC")
    fun sortTasksByDeadlineDescWithIncompleteFilter(): Flow<List<TaskModel>>


    @Query("SELECT * FROM taskModel WHERE deadline IS NOT NULL ORDER BY deadline DESC")
    fun sortTasksByDeadlineDescWithDeadlineFilter(): Flow<List<TaskModel>>

    @Query("SELECT * FROM taskModel WHERE deadline IS NULL ORDER BY deadline DESC")
    fun sortTasksByDeadlineDescWithNoDeadlineFilter(): Flow<List<TaskModel>>

}