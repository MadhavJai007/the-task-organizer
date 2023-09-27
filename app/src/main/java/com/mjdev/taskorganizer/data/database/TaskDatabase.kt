package com.mjdev.taskorganizer.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mjdev.taskorganizer.data.daos.TaskDao
import com.mjdev.taskorganizer.data.models.TaskModel
import com.mjdev.taskorganizer.data.database.TypeConverter

@Database(
    entities = [TaskModel::class],
    version = 2,
//    autoMigrations = [
//        AutoMigration (from = 1, to = 2)
//    ],
//    exportSchema = true
)
@TypeConverters(TypeConverter::class)
abstract class TaskDatabase : RoomDatabase(){
    abstract fun taskDao() : TaskDao

    companion object {
        private var INSTANCE: TaskDatabase? = null
        fun getInstance(context: Context): TaskDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "Task_database"
                    )
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}