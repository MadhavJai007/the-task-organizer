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
import java.time.LocalDate
import java.time.LocalDateTime

class UpdateTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepo = TaskRepository(application)
    var taskModel by mutableStateOf(TaskModel(0, "", "" , LocalDateTime.now() ))
    fun getTaskById(noteId: Int) {
        viewModelScope.launch {
            taskRepo.getTaskByIdFromRoom(noteId).collect { response ->
                taskModel = response
            }
        }
    }

    fun updateTasks(taskModel: TaskModel) {
        viewModelScope.launch {
//            taskRepo.updateTaskInRoom(taskModel)
            taskRepo.upsertTaskInRoom(taskModel)
        }
    }

    fun deleteTask(taskModel: TaskModel){
        viewModelScope.launch {
            taskRepo.deleteTaskFromRoom(taskModel)
        }
    }

    fun updateTitle(title: String) {
        taskModel = taskModel.copy(title = title)
    }

    fun updateNote(note: String) {
        taskModel = taskModel.copy(notes = note)
    }

    fun updateDeadline(dateTime: LocalDateTime?){
        taskModel = taskModel.copy(deadline = dateTime)
    }
}

