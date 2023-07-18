package com.example.taskorganizaer.ui.presentation.updateNoteScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.taskorganizaer.R
import com.example.taskorganizaer.data.models.NoteModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateNoteTopBar(
    viewModel: UpdateNoteViewModel,
    noteId: Int,
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
                val updateNote = NoteModel(noteId, title, note)
                viewModel.updateNotes(updateNote)
                navigateBack()
            }) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = "save")
            }
        }
    )
}