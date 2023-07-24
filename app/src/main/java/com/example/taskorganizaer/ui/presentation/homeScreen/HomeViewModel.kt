package com.example.taskorganizaer.ui.presentation.homeScreen

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskorganizaer.data.models.TaskModel
import com.example.taskorganizaer.domain.TaskRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepo = TaskRepository(application)
    var tasksModel by mutableStateOf(emptyList<TaskModel>())

    fun getAllTasks() {
        viewModelScope.launch {
            taskRepo.getAllTasksFromRoom().collect { response ->
                tasksModel = response
            }
        }
    }

    fun deleteTask(taskModel: TaskModel) {
        viewModelScope.launch {
            taskRepo.deleteTaskFromRoom(taskModel)
        }
    }
}