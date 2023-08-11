package com.exmaple.taskorganizaer.ui.presentation.addTaskScreen

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
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
import com.example.taskorganizaer.ui.presentation.components.DeadlinePickerDialog
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    navigateBack: () -> Unit,
) {
    val viewModel: AddTaskViewModel = viewModel()
    var title by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var deadline: LocalDateTime? by remember { mutableStateOf(null)}
    val focusManager = LocalFocusManager.current

    val maxTitleCharLength = 64
    val maxNoteCharLength = 1000

    var showDatePickerDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { AddTaskTopBar(viewModel, navigateBack, title, notes, deadline = deadline) },
//        backgroundColor = colorScheme.surface
        containerColor = MaterialTheme.colorScheme.surface
    ) { padding ->

        Surface(
//            color =colorResource(id = R.color.colorBackground),
//            shape = RoundedCornerShape(32.dp, 32.dp),

        ) {

            if(showDatePickerDialog){
                if(deadline!= null){
                    val itsaDate: LocalDateTime = deadline!!
                    DeadlinePickerDialog(
                        dismissDialog = { showDatePickerDialog = false },
                        updateDeadline = { deadline = it },
                        deadlineDate = itsaDate.toLocalDate(),
                        deadlineTime = itsaDate.toLocalTime()
                    )
                }
                else {
                    DeadlinePickerDialog(
                        dismissDialog = { showDatePickerDialog = false },
                        updateDeadline = { deadline = it }
                    )
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly ,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    TextField(
                        value = title,
                        onValueChange = {
                            if(it.length <= maxTitleCharLength){
                                title = it
                            }
                        },
                        placeholder = {
                            Text(
                                text= "Title",
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.alpha(0.7f)
                            )
                        },
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 18.sp
//                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_regular)),
                        ),

//                    supportingText = {
//                        Box(){
//
//                        }
//                    },
                        modifier = Modifier
                            .fillMaxWidth(0.85f),
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
                            focusManager.moveFocus(FocusDirection.Down) }
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
//                    Box(
//                        modifier = Modifier,
//                        vertic
////                            .fillMaxSize()
//                    ){
                        Text(
                            text = "${title.length}/$maxTitleCharLength",
                            modifier = Modifier
                                .padding(end = 8.dp),
                            fontSize = 12.sp
                        )
//                    }

                }
                
                Divider(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    thickness = 4.dp
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, end = 8.dp)
                ){
                    Row (){
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
                            if (deadline != null) Text(
                                text = "DEADLINE: ${deadline!!.dayOfMonth} ${deadline!!.month} ${deadline!!.year}, ${deadline!!.hour}:${deadline!!.minute}".trimIndent(),
                                fontSize = 10.sp
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${notes.length}/$maxNoteCharLength",
                            fontSize = 12.sp ,
                            modifier = Modifier
//                                .align(Alignment.CenterEnd)
                        )
                    }

                }
                TextField(
                    value = notes,
                    onValueChange = {
                        if(it.length <= maxNoteCharLength)
                            notes = it
                    },
                    placeholder = {
                        Text(
                            text="Put notes about your task here...",
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