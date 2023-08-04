package com.example.taskorganizaer.ui.presentation.homeScreen

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskorganizaer.R
import com.example.taskorganizaer.data.models.SortTypes
import com.example.taskorganizaer.data.models.TaskModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onFabClicked: () -> Unit,
    navigateToUpdateTaskScreen: (taskId: Int) -> Unit,
    navigateToAboutScreen: () -> Unit,
) {
    val homeViewModel: HomeViewModel = viewModel()
    val tasks = homeViewModel.tasks
    LaunchedEffect(Unit) {
        homeViewModel.sortTasks(SortTypes.TITLE_ASC)
//        viewModel.getAllTasks()
    }
    Scaffold(
        topBar = { HomeTopBar(
            navigateToAboutScreen = navigateToAboutScreen,
            homeViewModel = homeViewModel
        ) },
//        bottomBar = {
//            BottomAppBar (
//                actions = {},
//
//            )
//        },
        floatingActionButton = {
            MoveableFAB(
                onFabClicked = onFabClicked
            )
//            FloatingActionButton(
//                modifier = Modifier,
////                            .padding(0.dp, 0.dp, 20.dp, 32.dp),
//                onClick = { onFabClicked() },
//                containerColor = MaterialTheme.colorScheme.primary
//            ) {
//                Icon(
//                    Icons.Filled.Add,
//                    contentDescription = "add")
//            }
        },
        floatingActionButtonPosition = FabPosition.End
//        backgroundColor = MaterialTheme.colorScheme.surface
    ) { padding ->
        Surface(
            shape = RoundedCornerShape(32.dp, 32.dp),
//            color = colorResource(id = R.color.colorBackground)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 70.dp, 0.dp, 0.dp)
            ) {
                if (tasks.isNotEmpty()) {
                    items(
                        tasks
                    ) { taskModel ->
//                        NoteSwappable(noteModel, viewModel, navigateToUpdateNoteScreen)
                        TaskCard(taskModel = taskModel, homeViewModel = homeViewModel, navigateToUpdateTaskScreen = navigateToUpdateTaskScreen)
                        Divider(
                            color = MaterialTheme.colorScheme.background,
                            thickness = 8.dp
                        )
                    }
                } else {
                    item {
                        ShowTasks()
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
fun ShowTasks() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(0.dp, 120.dp, 0.dp, 0.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), //painter = painterResource(id = R.drawable.img),
            contentDescription = "empty",
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.Center
        )
        Text(text = "Your tasks will show here",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TaskCard(
    taskModel: TaskModel,
    homeViewModel: HomeViewModel,
    navigateToUpdateTaskScreen: (taskId: Int) -> Unit,
) {

    var moreActionsMenuExpanded by remember { mutableStateOf(false) }
    var taskNotesExpanded by remember { mutableStateOf(false)}

    // preview notes expand animation
    val extraPadding by animateDpAsState(
        targetValue = if(taskNotesExpanded) 12.dp else 8.dp,
        label = "",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Card(
        modifier = Modifier
//            .heightIn(0.dp, 200.dp)
            .fillMaxWidth()
            .padding(20.dp, 5.dp)
            .clickable {
                navigateToUpdateTaskScreen(taskModel.id)
                Log.i("HomeScreen", "onCardClicked")
            },
//        backgroundColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
//            pressedElevation = 9.dp
        )
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
//            .padding(24.dp, 6.dp)
            .padding(24.dp, extraPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "ID:${taskModel.id}.  ${taskModel.title}",
                    modifier = Modifier
                        .padding(top = 6.dp, bottom = 18.dp),
                    fontSize = 24.sp,
//                fontFamily = FontFamily(Font(R.font.playfair_display_regular)),

                )
                Box() {
                    IconButton(onClick = { moreActionsMenuExpanded = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Delete task"
                        )
                    }
                    DropdownMenu(
                        expanded = moreActionsMenuExpanded,
                        onDismissRequest = { moreActionsMenuExpanded = false })
                    {
                        DropdownMenuItem(
                            text = {
                                Text(text = "Delete")
                            },
                            onClick = {
                                homeViewModel.deleteTask(taskModel)
                                moreActionsMenuExpanded = false
                            }
                        )
                    }
                }
            }

            Text(
                text = if (taskNotesExpanded)
                    taskModel.notes
                else
                    if(taskModel.notes.length > 70)
                        taskModel.notes.dropLast(taskModel.notes.length - 70) + "..."
                    else
                        taskModel.notes ,
                modifier = Modifier
                    .padding(bottom = 12.dp),
//                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_regular)),
                lineHeight = 18.sp
            )
            if(taskModel.notes.length >70)
                Row(
                    modifier = Modifier
                ){
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                    IconButton(
                        onClick = { taskNotesExpanded = !taskNotesExpanded }
                    ) {
                        Icon(
                            if(taskNotesExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = "Expand notes section"
                        )
                    }
                }

        }

    }
}