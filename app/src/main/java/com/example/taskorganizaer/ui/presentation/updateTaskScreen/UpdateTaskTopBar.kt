package com.example.taskorganizaer.ui.presentation.updateTaskScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.taskorganizaer.data.models.TaskModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTaskTopBar(
    viewModel: UpdateTaskViewModel,
    taskId: Int,
    navigateBack: () -> Unit,
    title: String,
    note: String,
) {
    CenterAlignedTopAppBar(
        title = { Text(
            text = title,
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
            IconButton(onClick = {
                val updatedTask = TaskModel(taskId, title, note)
                viewModel.updateTasks(updatedTask)
                navigateBack()
            }) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = "save")
            }
        }
    )
}