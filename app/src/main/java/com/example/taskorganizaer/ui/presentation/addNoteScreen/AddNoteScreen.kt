package com.exmaple.taskorganizaer.ui.presentation.addNoteScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    navigateBack: () -> Unit,
) {
    val viewModel: AddNoteViewModel = viewModel()
    var title by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = { AddNoteTopBar(viewModel, navigateBack, title, notes) },
//        backgroundColor = colorScheme.surface
        containerColor = colorScheme.surface
    ) { padding ->
        Surface(
//            color =colorResource(id = R.color.colorBackground),
            shape = RoundedCornerShape(32.dp, 32.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text("Title", color = colorScheme.onSurface) },
                    textStyle = TextStyle(color = colorScheme.onSurface
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
                        focusManager.moveFocus(FocusDirection.Down) }
                    ),
                )
                TextField(
                    value = notes,
                    onValueChange = { notes = it },
                    placeholder = { Text("Notes", color = colorScheme.onSurface) },
                    textStyle = TextStyle(color = colorScheme.onSurface,
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