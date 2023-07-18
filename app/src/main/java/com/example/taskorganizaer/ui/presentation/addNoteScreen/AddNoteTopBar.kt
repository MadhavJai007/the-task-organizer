package com.exmaple.taskorganizaer.ui.presentation.addNoteScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.taskorganizaer.data.models.NoteModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteTopBar(
    viewModel: AddNoteViewModel,
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
                    val noteModel = NoteModel(id = 0, title = title, notes = notes)
                    viewModel.insertNote(noteModel)
                    navigateBack()
                }else{
                    navigateBack()
                }
            }) {
                Icon(Icons.Default.Check, //painterResource(id = R.drawable.ic_baseline_check_24),
                    contentDescription = "Add note")
            }
        }
    )
}