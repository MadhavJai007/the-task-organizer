package com.example.taskorganizer.ui.presentation.updateTaskScreen

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskorganizer.data.models.TaskModel
import com.example.taskorganizer.ui.presentation.components.DeadlinePickerDialog
import com.marosseleng.compose.material3.datetimepickers.date.ui.dialog.DatePickerDialog
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UpdateTaskScreen(
    taskId: Int,
    navigateBack: () -> Unit,
) {
    val viewModel: UpdateTaskViewModel = viewModel()
    val title = viewModel.taskModel.title
    val note = viewModel.taskModel.notes
    val dateCreated = viewModel.taskModel.dateCreated
    val dateModified = viewModel.taskModel.dateModified
    val dateCompleted = viewModel.taskModel.dateCompleted
    val deadline = viewModel.taskModel.deadline
    val focusManager = LocalFocusManager.current

    val maxTitleCharLength = 64
    val maxNoteCharLength = 1000

    var showDatePickerDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getTaskById(taskId)
    }
    Scaffold(
        topBar = { UpdateTaskTopBar(viewModel, taskId, navigateBack, title, note , dateCreated, dateCompleted = dateCompleted, deadline = deadline) },
        containerColor = MaterialTheme.colorScheme.surface

    ) { padding ->


        Surface(
//            color = colorResource(id = R.color.colorBackground),
//            shape = RoundedCornerShape(2.dp, 2.dp),
            modifier = Modifier
                .padding(padding)
        ) {

            if(showDatePickerDialog){
                DeadlinePickerDialog(
                    dismissDialog = { showDatePickerDialog = false },
                    updateDeadline = viewModel::updateDeadline,
                    deadlineDate = viewModel.taskModel.deadline?.toLocalDate(),
                    deadlineTime = viewModel.taskModel.deadline?.toLocalTime()
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly ,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    TextField(
                        value = title,
                        onValueChange = {
                                title -> if(title.length <= maxTitleCharLength) {
                                    viewModel.updateTitle(title)
                                }
                        },
                        placeholder = {
                            Text(
                                text = "Title",
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.alpha(0.7f)
                            )
                        },
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 18.sp,
//                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_regular)),
                        ),
                        modifier = Modifier.fillMaxWidth(0.85f),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            capitalization = KeyboardCapitalization.Sentences,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "${title.length}/$maxTitleCharLength",
                        modifier = Modifier
                            .padding(end = 8.dp),
                        fontSize = 12.sp
                    )

                }
                Divider(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    thickness = 4.dp
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()

                ){
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp, top=12.dp)
                    ){
                        IconButton(onClick = {
//                            viewModel.updateTasks(updatedTask)
//                            navigateBack()
                            showDatePickerDialog = true
                        }) {
                            Icon(
                                Icons.Filled.Event,
                                contentDescription = "pick deadline")
                        }

                        Spacer(modifier = Modifier.weight(1f))
                        Column {

                            if (dateModified != null) Text(
                                text = "Last modified: ${dateModified.dayOfMonth} ${dateModified.month} ${dateModified.year}, ${dateModified.hour}:${dateModified.minute}".trimIndent(),
                                fontSize = 10.sp
                            )
                            Text(
                                text = "Added: ${dateCreated.dayOfMonth} ${dateCreated.month} ${dateCreated.year}, ${dateCreated.hour}:${dateCreated.minute}".trimIndent(),
                                fontSize = 10.sp
                            )
                            if (deadline != null) Text(
                                text = "DEADLINE: ${deadline.dayOfMonth} ${deadline.month} ${deadline.year}, ${deadline.hour}:${deadline.minute}".trimIndent(),
                                fontSize = 10.sp
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${note.length}/$maxNoteCharLength",
                            fontSize = 12.sp ,
                            modifier = Modifier
                                .padding(top = 16.dp, end = 8.dp)
//                                .align(Alignment.CenterEnd)
                        )
                    }
                }
                TextField(
                    value = note,
                    onValueChange = {
                            note -> if(note.length <= maxNoteCharLength){
                                viewModel.updateNote(note)
                            }
                    },
                    placeholder = {
                        Text(
                            text = "Note",
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.alpha(0.7f)
                        )
                    },
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface,
//                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_regular)),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Sentences,
                        keyboardType = KeyboardType.Text,
                    ),
                )
            }
        }
    }
}