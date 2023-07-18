package com.example.taskorganizaer.ui.presentation.homeScreen

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskorganizaer.R
import com.example.taskorganizaer.data.models.NoteModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onFabClicked: () -> Unit,
    navigateToUpdateNoteScreen: (noteId: Int) -> Unit,
    navigateToAboutScreen: () -> Unit,
) {
    val viewModel: HomeViewModel = viewModel()
    val notesModel = viewModel.notesModel
    LaunchedEffect(Unit) {
        viewModel.getAllNotes()
    }
    Scaffold(
        topBar = { HomeTopBar(navigateToAboutScreen) },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 32.dp),
                onClick = { onFabClicked() },
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "add")
            }
        },
//        backgroundColor = MaterialTheme.colorScheme.surface
    ) { padding ->
        Surface(
            shape = RoundedCornerShape(32.dp, 32.dp),
//            color = colorResource(id = R.color.colorBackground)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
            ) {
                if (notesModel.isNotEmpty()) {
                    items(notesModel) { noteModel ->
//                        NoteSwappable(noteModel, viewModel, navigateToUpdateNoteScreen)
                        NotesCard(noteModel = noteModel, viewModel = viewModel, navigateToUpdateNoteScreen = navigateToUpdateNoteScreen)
                    }
                } else {
                    item {
                        ShowNoNotes()
                    }
                }
            }
        }
    }
}


//@Composable
//fun NoteSwappable(
//    noteModel: NoteModel,
//    viewModel: HomeViewModel,
//    navigateToUpdateNoteScreen: (noteId: Int) -> Unit,
//) {
//    val dismissState = rememberDismissState(
//        confirmStateChange = {
//            if (it == DismissedToEnd)
//                viewModel.deleteNote(noteModel)
//            it != DismissedToEnd
//        }
//    )
//    SwipeToDismiss(directions = setOf(StartToEnd), state = dismissState, background = {
//        val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
//        val color by animateColorAsState(
//            when (dismissState.targetValue) {
//                Default -> Color.LightGray
//                DismissedToEnd -> Color.Red
//                DismissedToStart -> return@SwipeToDismiss
//            }
//        )
//        val alignment = when (direction) {
//            StartToEnd -> Alignment.CenterStart
//            EndToStart -> return@SwipeToDismiss
//        }
//        val scale by animateFloatAsState(
//            if (dismissState.targetValue == Default) 0.75f else 1f
//        )
//        Box(
//            Modifier
//                .fillMaxSize()
//                .background(color)
//                .padding(horizontal = 20.dp),
//            contentAlignment = alignment
//        ) {
//            Icon(
//                Icons.Default.Delete,
//                contentDescription = "delete",
//                modifier = Modifier.scale(scale)
//            )
//        }
//    }, dismissThresholds = {
//        androidx.compose.material.FractionalThreshold(0.25f)
//    }) {
//        NotesCard(noteModel, navigateToUpdateNoteScreen)
//    }
//}


@Composable
fun ShowNoNotes() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(0.dp, 120.dp, 0.dp, 0.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), //painter = painterResource(id = R.drawable.img),
            contentDescription = "empty",
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.Center
        )
        Text(text = "Your notes will show here",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun NotesCard(
    noteModel: NoteModel,
    viewModel: HomeViewModel,
    navigateToUpdateNoteScreen: (noteId: Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .heightIn(0.dp, 188.dp)
            .fillMaxWidth()
            .padding(20.dp, 5.dp)
            .clickable {
                navigateToUpdateNoteScreen(noteModel.id)
                Log.i("HomeScreen", "onCardClicked")
            },
//        backgroundColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(24.dp)
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp, 6.dp)
        ) {
            Text(
                text = noteModel.title,
                fontSize = 24.sp,
//                fontFamily = FontFamily(Font(R.font.playfair_display_regular)),

                )
            Text(
                text = noteModel.notes,
//                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_regular)),
                lineHeight = 17.sp
            )
        }
        IconButton(onClick = { viewModel.deleteNote(noteModel) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete task" )
        }
    }
}