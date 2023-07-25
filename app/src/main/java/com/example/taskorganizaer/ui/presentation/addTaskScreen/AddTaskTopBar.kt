package com.exmaple.taskorganizaer.ui.presentation.addTaskScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.taskorganizaer.data.models.TaskModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskTopBar(
    viewModel: AddTaskViewModel,
    navigateBack: () -> Unit,
    title: String,
    notes: String,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                "Notes",
//                fontFamily = FontFamily(Font(R.font.playfair_display_regular)),
            )
        },
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(Icons.Default.ArrowBack ,
                    contentDescription = "Go back")
            }
        },
        actions = {
            IconButton(onClick = {
                if (title.isNotEmpty() || notes.isNotEmpty()){
                    val taskModel = TaskModel(id = 0, title = title, notes = notes)
                    viewModel.insertNote(taskModel)
                    navigateBack()
                }else{
                    navigateBack()
                }
            }) {
                Icon(Icons.Default.Check, //painterResource(id = R.drawable.ic_baseline_check_24),
                    contentDescription = "Add task")
            }
        }
    )
}