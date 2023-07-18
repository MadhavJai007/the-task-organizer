package com.example.taskorganizaer.ui

sealed class TaskAppScreen(val name : String){
    object Home : TaskAppScreen("home")
    object AddNotes : TaskAppScreen("add_note")
    object UpdateNotes : TaskAppScreen("update_note/{id}")
    object About:TaskAppScreen("about")
}