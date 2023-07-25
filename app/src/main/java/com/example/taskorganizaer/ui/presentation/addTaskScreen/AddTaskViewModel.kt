package com.exmaple.taskorganizaer.ui.presentation.addTaskScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskorganizaer.data.models.TaskModel
import com.example.taskorganizaer.domain.TaskRepository
import kotlinx.coroutines.launch

class AddTaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepo = TaskRepository(application)

    fun insertNote(taskModel: TaskModel) {
        viewModelScope.launch {
            taskRepo.upsertTaskInRoom(taskModel)
        }
    }
}