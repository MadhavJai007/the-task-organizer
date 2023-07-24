package com.example.taskorganizaer.ui.presentation.updateTaskScreen

import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.getTaskById(taskId)
    }
    Scaffold(
        topBar = { UpdateTaskTopBar(viewModel, taskId, navigateBack, title, note) },
        containerColor = MaterialTheme.colorScheme.surface

    ) { padding ->
        Surface(
//            color = colorResource(id = R.color.colorBackground),
            shape = RoundedCornerShape(32.dp, 32.dp),
            modifier = Modifier
                .padding(padding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                TextField(
                    value = title,
                    onValueChange = { title -> viewModel.updateTitle(title) },
                    placeholder = { Text(text = "Title",color = MaterialTheme.colorScheme.onSurface) },
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
//                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_regular)),
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
//                        backgroundColor = Color.Transparent,
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

                TextField(
                    value = note,
                    onValueChange = { note -> viewModel.updateNote(note) },
                    placeholder = { Text(text = "Note",color = MaterialTheme.colorScheme.onSurface) },
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface,
//                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_regular)),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f),
                    colors = TextFieldDefaults.textFieldColors(
//                        containerColor = Color.Transparent,
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