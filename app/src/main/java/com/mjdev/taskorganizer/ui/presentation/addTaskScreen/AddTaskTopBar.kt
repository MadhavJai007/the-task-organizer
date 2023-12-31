package com.exmaple.taskorganizaer.ui.presentation.addTaskScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.mjdev.taskorganizer.data.models.TaskModel
import java.time.LocalDateTime
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskTopBar(
    viewModel: AddTaskViewModel,
    navigateBack: () -> Unit,
    title: String,
    notes: String,
    deadline: LocalDateTime?
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                "Creating...",
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
                    val taskModel = TaskModel(id = 0, title = title, notes = notes, dateCreated = LocalDateTime.now(), dateModified = LocalDateTime.now() , deadline = deadline)
                    viewModel.insertNote(taskModel)
                    navigateBack()
                }else{
                    navigateBack()
                }
            }) {
                Icon(Icons.Default.Save, //painterResource(id = R.drawable.ic_baseline_check_24),
                    contentDescription = "Add task")
            }
        }
    )
}