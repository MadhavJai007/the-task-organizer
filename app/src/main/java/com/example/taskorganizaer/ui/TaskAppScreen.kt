package com.example.taskorganizaer.ui

sealed class TaskAppScreen(val name : String){
    object Home : TaskAppScreen("home")
    object AddTasks : TaskAppScreen("add_task")
    object UpdateTasks : TaskAppScreen("update_task/{id}")
    object About:TaskAppScreen("about")
}