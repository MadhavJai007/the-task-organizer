package com.mjdev.taskorganizer.ui.presentation.homeScreen

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mjdev.taskorganizer.data.models.FilterTypes
import com.mjdev.taskorganizer.data.models.SortTypes
import com.mjdev.taskorganizer.data.models.TaskModel
import com.mjdev.taskorganizer.domain.TaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _sortType = MutableStateFlow(SortTypes.TITLE_ASC)
    private val _taskRepo = TaskRepository(application)
    @OptIn(ExperimentalCoroutinesApi::class)
    var _tasks = mutableStateOf(
        _sortType
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
            _taskRepo.sortTasksByDateModifiedDescWithIncompleteFilter().collect { response ->
                tasks = response
            }
        }
    }

    fun sortAndFilterTasks(sortType: SortTypes, filterType: FilterTypes) {
//        when(filterType) {
//
//            FilterTypes.SHOW_ALL_TASKS_COMPLETION ->
//        }

        if(sortType == SortTypes.DATE_MODIFIED_ASC && filterType == FilterTypes.SHOW_INCOMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateModifiedAscWithIncompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DATE_MODIFIED_ASC && filterType == FilterTypes.SHOW_COMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateModifiedAscWithCompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DATE_MODIFIED_ASC && filterType == FilterTypes.SHOW_ALL_TASKS_COMPLETION){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateModifiedAsc().collect { response ->
                    tasks = response
                }
            }
        }

        else if(sortType == SortTypes.DATE_MODIFIED_DESC && filterType == FilterTypes.SHOW_INCOMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateModifiedDescWithIncompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DATE_MODIFIED_DESC && filterType == FilterTypes.SHOW_COMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateModifiedDescWithCompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DATE_MODIFIED_DESC && filterType == FilterTypes.SHOW_ALL_TASKS_COMPLETION){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateModifiedDesc().collect { response ->
                    tasks = response
                }
            }
        }

        else if(sortType == SortTypes.DATE_CREATED_ASC && filterType == FilterTypes.SHOW_INCOMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateCreatedAscWithIncompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DATE_CREATED_ASC && filterType == FilterTypes.SHOW_COMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateCreatedAscWithCompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DATE_CREATED_ASC && filterType == FilterTypes.SHOW_ALL_TASKS_COMPLETION){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateCreatedAsc().collect { response ->
                    tasks = response
                }
            }
        }

        else if(sortType == SortTypes.DATE_CREATED_DESC && filterType == FilterTypes.SHOW_INCOMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateCreatedDescWithIncompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DATE_CREATED_DESC && filterType == FilterTypes.SHOW_COMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateCreatedDescWithCompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DATE_CREATED_DESC && filterType == FilterTypes.SHOW_ALL_TASKS_COMPLETION){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateCreatedDesc().collect { response ->
                    tasks = response
                }
            }
        }

        else if(sortType == SortTypes.DATE_COMPLETION_ASC && filterType == FilterTypes.SHOW_INCOMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateCompletedAscWithIncompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DATE_COMPLETION_ASC && filterType == FilterTypes.SHOW_COMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateCompletedAscWithCompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DATE_COMPLETION_ASC && filterType == FilterTypes.SHOW_ALL_TASKS_COMPLETION){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateCompletedAsc().collect { response ->
                    tasks = response
                }
            }
        }

        else if(sortType == SortTypes.DATE_COMPLETION_DESC && filterType == FilterTypes.SHOW_INCOMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateCompletedDescWithIncompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DATE_COMPLETION_DESC && filterType == FilterTypes.SHOW_COMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateCompletedDescWithCompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DATE_COMPLETION_DESC && filterType == FilterTypes.SHOW_ALL_TASKS_COMPLETION){
            viewModelScope.launch {
                _taskRepo.sortTasksByDateCompletedDesc().collect { response ->
                    tasks = response
                }
            }
        }

        else if(sortType == SortTypes.DEADLINE_ASC && filterType == FilterTypes.SHOW_INCOMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDeadlineAscWithIncompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DEADLINE_ASC && filterType == FilterTypes.SHOW_COMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDeadlineAscWithCompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DEADLINE_ASC && filterType == FilterTypes.SHOW_ALL_TASKS_COMPLETION){
            viewModelScope.launch {
                _taskRepo.sortTasksByDeadlineAsc().collect { response ->
                    tasks = response
                }
            }
        }

        else if(sortType == SortTypes.DEADLINE_DESC && filterType == FilterTypes.SHOW_INCOMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDeadlineDescWithIncompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DEADLINE_DESC && filterType == FilterTypes.SHOW_COMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByDeadlineDescWithCompleteFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.DEADLINE_DESC && filterType == FilterTypes.SHOW_ALL_TASKS_COMPLETION){
            viewModelScope.launch {
                _taskRepo.sortTasksByDeadlineDesc().collect { response ->
                    tasks = response
                }
            }
        }

        else if(sortType == SortTypes.TITLE_ASC && filterType == FilterTypes.SHOW_INCOMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByAlphaAscWithIncompletedFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.TITLE_ASC && filterType == FilterTypes.SHOW_COMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByAlphaAscWithCompletedFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.TITLE_ASC && filterType == FilterTypes.SHOW_ALL_TASKS_COMPLETION){
            viewModelScope.launch {
                _taskRepo.sortTasksByAlphaAsc().collect { response ->
                    tasks = response
                }
            }
        }

        else if(sortType == SortTypes.TITLE_DESC && filterType == FilterTypes.SHOW_INCOMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByAlphaDescWithIncompletedFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.TITLE_DESC && filterType == FilterTypes.SHOW_COMPLETE_TASKS){
            viewModelScope.launch {
                _taskRepo.sortTasksByAlphaDescWithCompletedFilter().collect { response ->
                    tasks = response
                }
            }
        }
        else if(sortType == SortTypes.TITLE_DESC && filterType == FilterTypes.SHOW_ALL_TASKS_COMPLETION){
            viewModelScope.launch {
                _taskRepo.sortTasksByAlphaDesc().collect { response ->
                    tasks = response
                }
            }
        }


//        when(sortType) {
//            SortTypes.BY_ID_ASC -> viewModelScope.launch {
//                _taskRepo.getAllTasksByIDAsc().collect { response ->
//                    tasks = response
//                }
//            }
//            SortTypes.BY_ID_DESC -> viewModelScope.launch {
//                _taskRepo.sortTasksByIDDesc().collect { response ->
//                    tasks = response
//                }
//            }
//            SortTypes.TITLE_ASC -> viewModelScope.launch {
//                _taskRepo.sortTasksByAlphaAsc().collect { response ->
//                    tasks = response
//                }
//            }
//            SortTypes.TITLE_DESC -> viewModelScope.launch {
//                _taskRepo.sortTasksByAlphaDesc().collect { response ->
//                    tasks = response
//                }
//            }
//            else -> viewModelScope.launch {
//                _taskRepo.getAllTasksByIDAsc().collect { response ->
//                    tasks = response
//                }
//            }
//        }

    }

    fun completeTask(taskModel: TaskModel) {
        val completedTask = taskModel.copy(dateCompleted = LocalDateTime.now())

        viewModelScope.launch {
            _taskRepo.upsertTaskInRoom(completedTask)
        }

    }

    fun incompleteTask(taskModel: TaskModel) {
        val resumedTask = taskModel.copy(dateCompleted = null)

        viewModelScope.launch {
            _taskRepo.upsertTaskInRoom(resumedTask)
        }

    }

    fun deleteTask(taskModel: TaskModel) {
        viewModelScope.launch {
            _taskRepo.deleteTaskFromRoom(taskModel)
        }
    }
}