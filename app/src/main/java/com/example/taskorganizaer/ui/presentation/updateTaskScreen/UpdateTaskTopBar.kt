package com.example.taskorganizaer.ui.presentation.updateTaskScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.taskorganizaer.data.models.TaskModel
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTaskTopBar(
    viewModel: UpdateTaskViewModel,
    taskId: Int,
    navigateBack: () -> Unit,
    title: String,
    note: String,
    dateCreated: LocalDateTime
) {
    CenterAlignedTopAppBar(
        title = { Text(
            text = "Editing...",
//            fontFamily = FontFamily(Font(R.font.playfair_display_regular)),
        )
        },
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "back")
            }
        },
        actions = {
//            IconButton(onClick = {
//                navigateBack()
//                val task = TaskModel(taskId, title, note)
//                viewModel.deleteTask(taskModel = task)
//
//            }) {
//                Icon(
//                    imageVector = Icons.Default.Delete,
//                    contentDescription = "Delete task" )
//            }
            IconButton(onClick = {
                val updatedTask = TaskModel(taskId, title, note , dateCreated)
                viewModel.updateTasks(updatedTask)
                navigateBack()
            }) {
                Icon(
                    Icons.Default.Save,
                    contentDescription = "save task")
            }
        }
    )
}