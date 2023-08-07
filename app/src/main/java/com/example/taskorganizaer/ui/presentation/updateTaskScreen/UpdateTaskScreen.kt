package com.example.taskorganizaer.ui.presentation.updateTaskScreen

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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
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
    val focusManager = LocalFocusManager.current

    val maxTitleCharLength = 64
    val maxNoteCharLength = 1000

    LaunchedEffect(Unit) {
        viewModel.getTaskById(taskId)
    }
    Scaffold(
        topBar = { UpdateTaskTopBar(viewModel, taskId, navigateBack, title, note , dateCreated) },
        containerColor = MaterialTheme.colorScheme.surface

    ) { padding ->
        Surface(
//            color = colorResource(id = R.color.colorBackground),
//            shape = RoundedCornerShape(2.dp, 2.dp),
            modifier = Modifier
                .padding(padding)
        ) {
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
                        Column {

                            if (dateModified != null) Text(
                                text = "Last modified: ${dateModified.dayOfMonth} ${dateModified.month} ${dateModified.year}, ${dateModified.hour}:${dateModified.minute}".trimIndent(),
                                fontSize = 10.sp
                            )
                            Text(
                                text = "Added: ${dateCreated.dayOfMonth} ${dateCreated.month} ${dateCreated.year}, ${dateCreated.hour}:${dateCreated.minute}".trimIndent(),
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