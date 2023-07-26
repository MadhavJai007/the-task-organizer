package com.example.taskorganizaer.ui.presentation.homeScreen

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskorganizaer.data.models.SortTypes
import com.example.taskorganizaer.data.models.TaskModel
import com.example.taskorganizaer.domain.TaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _sortType = MutableStateFlow(SortTypes.TITLE_ASC)
    private val _taskRepo = TaskRepository(application)
    @OptIn(ExperimentalCoroutinesApi::class)
    var _tasks = mutableStateOf(_sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortTypes.TITLE_ASC -> _taskRepo.sortTasksByAlphaAsc()
                SortTypes.TITLE_DESC -> _taskRepo.sortTasksByAlphaDesc()
                else -> _taskRepo.sortTasksByAlphaAsc()
            }
        })
//        .stateIn()
    var tasks by mutableStateOf(emptyList<TaskModel>())

    fun getAllTasks() {
        viewModelScope.launch {
            _taskRepo.getAllTasksByIDAsc().collect { response ->
                tasks = response
            }
        }
    }

    fun sortTasks(sortType: SortTypes) {
        when(sortType) {
            SortTypes.BY_ID_ASC -> viewModelScope.launch {
                _taskRepo.getAllTasksByIDAsc().collect { response ->
                    tasks = response
                }
            }
            SortTypes.BY_ID_DESC -> viewModelScope.launch {
                _taskRepo.sortTasksByIDDesc().collect { response ->
                    tasks = response
                }
            }
            SortTypes.TITLE_ASC -> viewModelScope.launch {
                _taskRepo.sortTasksByAlphaAsc().collect { response ->
                    tasks = response
                }
            }
            SortTypes.TITLE_DESC -> viewModelScope.launch {
                _taskRepo.sortTasksByAlphaDesc().collect { response ->
                    tasks = response
                }
            }
            else -> viewModelScope.launch {
                _taskRepo.getAllTasksByIDAsc().collect { response ->
                    tasks = response
                }
            }
        }

    }

    fun deleteTask(taskModel: TaskModel) {
        viewModelScope.launch {
            _taskRepo.deleteTaskFromRoom(taskModel)
        }
    }
}