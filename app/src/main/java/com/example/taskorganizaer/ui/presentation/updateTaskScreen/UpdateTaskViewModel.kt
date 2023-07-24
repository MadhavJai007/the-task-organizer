package com.example.taskorganizaer.ui.presentation.updateTaskScreen

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskorganizaer.data.models.TaskModel
import com.example.taskorganizaer.domain.TaskRepository
import kotlinx.coroutines.launch

class UpdateTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepo = TaskRepository(application)
    var taskModel by mutableStateOf(TaskModel(0, "", ""))

    fun getTaskById(noteId: Int) {
        viewModelScope.launch {
            taskRepo.getTaskByIdFromRoom(noteId).collect { response ->
                taskModel = response
            }
        }
    }

    fun updateTasks(taskModel: TaskModel) {
        viewModelScope.launch {
            taskRepo.updateTaskInRoom(taskModel)
        }
    }

    fun updateTitle(title: String) {
        taskModel = taskModel.copy(title = title)
    }

    fun updateNote(note: String) {
        taskModel = taskModel.copy(notes = note)
    }
}
