package com.example.taskorganizaer.ui.presentation.homeScreen

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskorganizaer.R
import com.example.taskorganizaer.data.models.FilterTypes
import com.example.taskorganizaer.data.models.SortTypes
import com.example.taskorganizaer.data.models.TaskModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onFabClicked: () -> Unit,
    navigateToUpdateTaskScreen: (taskId: Int) -> Unit,
    navigateToAboutScreen: () -> Unit,
) {
    val homeViewModel: HomeViewModel = viewModel()
    var tasks = homeViewModel.tasks


    var sortBottomSheetExpanded by remember { mutableStateOf(false)}
    var selectedSortType by rememberSaveable { mutableStateOf(SortTypes.DATE_MODIFIED_DESC)}
    var filterMenuExpanded by remember { mutableStateOf(false)}
    var selectedFilterCategory by rememberSaveable { mutableStateOf("TASK_COMPLETION")}
    var selectedFilterType by rememberSaveable { mutableStateOf(FilterTypes.SHOW_INCOMPLETE_TASKS)}

    LaunchedEffect(Unit) {
        homeViewModel.sortAndFilterTasks(selectedSortType, selectedFilterType)
//        homeViewModel.getAllTasks()
    }
    Scaffold(
        topBar = { HomeTopBar(
            navigateToAboutScreen = navigateToAboutScreen,
            homeViewModel = homeViewModel,
            toggleSortBottomSheet = { sortBottomSheetExpanded = !sortBottomSheetExpanded }
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
        },
        floatingActionButtonPosition = FabPosition.End
//        backgroundColor = MaterialTheme.colorScheme.surface
    ) { padding ->

        Surface(
            shape = RoundedCornerShape(32.dp, 32.dp),
//            color = colorResource(id = R.color.colorBackground)
        ) {

            if(sortBottomSheetExpanded){
                ModalBottomSheet(
                    onDismissRequest = {
                        sortBottomSheetExpanded = false
                    },
                    modifier = Modifier
                        .fillMaxHeight(0.4f)
                ) {

                    Column {
                        Text(
                            text = "Sort and Filter",
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(start= 16.dp)
                        ) {
                            Text(
                                text = "Task filter type",
                                fontSize = 18.sp
                            )

                            Box() {
                                IconButton(
                                    onClick = {
                                        filterMenuExpanded = true
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Sorting dropdown menu"
                                    )
                                }
                                DropdownMenu(
                                    expanded = filterMenuExpanded,
                                    onDismissRequest = { filterMenuExpanded = false },

                                    ) {
                                    DropdownMenuItem(
                                        text = { Text(text = "Task completion") },
                                        onClick = {
                                            filterMenuExpanded = false
                                            selectedFilterCategory = "TASK_COMPLETION"
                                        })
                                    DropdownMenuItem(
                                        text = { Text(text = "Task deadline") },
                                        onClick = {
                                            filterMenuExpanded = false
                                            selectedFilterCategory = "TASK_DEADLINE"
                                        })
                                }
                            }
                        }


                        Row(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .horizontalScroll(rememberScrollState())
                        ) {
                            FilterTypes.values().forEachIndexed{ index, filterType ->
                                if(filterType.category == selectedFilterCategory){
                                    Row(
                                        modifier = Modifier
                                            .padding(horizontal = 4.dp)
                                            .clickable {
                                                selectedFilterType = filterType
                                                homeViewModel.sortAndFilterTasks(
                                                    selectedSortType,
                                                    filterType
                                                )
                                            },
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        RadioButton(
                                            selected = selectedFilterType.displayStr == filterType.displayStr,
                                            onClick = {
                                                selectedFilterType = filterType
                                                homeViewModel.sortAndFilterTasks(selectedSortType, filterType)
                                            }
                                        )
                                        Text(
                                            text = filterType.displayStr
                                        )
                                    }
                                }
                            }
                        }

                        Divider(
                            modifier = Modifier
                                .width(4.dp)
                                .padding(8.dp)
                                .background(MaterialTheme.colorScheme.secondary)
                        )

                        Text(
                            text = "Sort by",
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(start= 16.dp)
                        )
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 4.dp, vertical = 8.dp)
                                .horizontalScroll(rememberScrollState())
                        ) {
                            SortTypes.values().forEachIndexed{ index, sortType ->
                                Row(
                                    modifier = Modifier
                                        .padding(horizontal = 4.dp)
                                        .clickable {
                                            selectedSortType = sortType
                                            homeViewModel.sortAndFilterTasks(
                                                sortType,
                                                selectedFilterType
                                            )
                                        },
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    RadioButton(
                                        selected = selectedSortType.displayStr == sortType.displayStr,
                                        onClick = {
                                            selectedSortType = sortType
                                            homeViewModel.sortAndFilterTasks(sortType, selectedFilterType)
                                        }
                                    )
                                    Text(
                                        text = sortType.displayStr
                                    )
                                }
                            }
                        }
                    }
                }
            }

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCard(
    taskModel: TaskModel,
    homeViewModel: HomeViewModel,
    navigateToUpdateTaskScreen: (taskId: Int) -> Unit,
) {

    var moreActionsMenuExpanded by remember { mutableStateOf(false) }
    var taskNotesExpanded by remember { mutableStateOf(false)}

    val notePreviewMaxLength: Int = 70

    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackState, Modifier)

    // preview notes expand animation
    val extraPadding by animateDpAsState(
        targetValue = if(taskNotesExpanded) 12.dp else 8.dp,
        label = "",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart){
                homeViewModel.deleteTask(taskModel)
                it != DismissValue.DismissedToStart
            }
            else {
                if(taskModel.dateCompleted != null)
                    homeViewModel.incompleteTask(taskModel)
                else
                    homeViewModel.completeTask(taskModel)
                it != DismissValue.DismissedToEnd
            }

        }
    )

    SwipeToDismiss(state = dismissState, background = {
        val color by animateColorAsState(
            when (dismissState.targetValue) {
                DismissValue.Default -> MaterialTheme.colorScheme.background
                DismissValue.DismissedToEnd -> Color.Green
                DismissValue.DismissedToStart -> Color.Red
            }, label = "swiped"
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(color))
    }, dismissContent = {
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//                contentAlignment = Alignment.TopEnd
                ){
                    Text(
                        text = "${if (taskModel.title == "") "Untitled" else taskModel.title}",
                        modifier = Modifier
                            .padding(top = 6.dp, bottom = 18.dp)
                            .alpha(if (taskModel.title == "" || taskModel.dateCompleted != null) 0.7f else 1f),
                        fontSize = 24.sp,
                        textDecoration = if(taskModel.dateCompleted != null) {
                            TextDecoration.LineThrough
                        }else{
                            TextDecoration.None
                        }
//                fontFamily = FontFamily(Font(R.font.playfair_display_regular)),

                    )

                }

                Text(
                    text = if (taskNotesExpanded)
                        taskModel.notes
                    else
                        if(taskModel.notes.length > notePreviewMaxLength)
                            taskModel.notes.dropLast(
                                taskModel.notes.length - notePreviewMaxLength
                            ) + "..."
                        else
                            taskModel.notes ,
                    modifier = Modifier
                        .padding(bottom = 12.dp),
//                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_regular)),
                    lineHeight = 18.sp
                )
                Row(
                    modifier = Modifier,
//                    .background(Color.Magenta)
//                HorizontalAlignment = Alignment.
                ){

                    Box(
//                    contentAlignment = Alignment.TopEnd,
                        modifier = Modifier
//                        .align(Alignment.TopEnd)
//                        .weight(1f)
                    ) {
                        IconButton(onClick = { moreActionsMenuExpanded = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Task actions dropdown menu",
                                modifier = Modifier
//                                .fillMaxWidth(0.15f)

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
                            if(taskModel.dateCompleted != null) {
                                DropdownMenuItem(
                                    text = {
                                        Text(text = "Mark as incomplete")
                                    },
                                    onClick = {
                                        homeViewModel.incompleteTask(taskModel)
                                        moreActionsMenuExpanded = false
                                    }
                                )
                            }else {
                                DropdownMenuItem(
                                    text = {
                                        Text(text = "Complete task")
                                    },
                                    onClick = {
                                        homeViewModel.completeTask(taskModel)
                                        moreActionsMenuExpanded = false
                                    }
                                )
                            }

                        }
                    }
                    Text(
                        text = if(taskModel.dateModified != null) {
                            "Last edited: ${taskModel.dateModified.dayOfMonth} ${taskModel.dateModified.month} ${taskModel.dateModified.year}, ${taskModel.dateModified.hour}:${taskModel.dateModified.minute}".trimIndent()
                        } else {
                            "Added: ${taskModel.dateCreated.dayOfMonth} ${taskModel.dateCreated.month} ${taskModel.dateCreated.year}, ${taskModel.dateCreated.hour}:${taskModel.dateCreated.minute}".trimIndent()
                        },
                        modifier = Modifier
                            .weight(1f)
//                            .background(Color.Magenta)
                            .padding(top = 16.dp)
                            .fillMaxWidth(0.7f)
//                        .background(Color.Magenta)
                        ,
                        textAlign = TextAlign.Center
                        ,

                        fontSize = 10.sp,

                        )
                    if(taskModel.notes.length > notePreviewMaxLength)
                        IconButton(
                            onClick = { taskNotesExpanded = !taskNotesExpanded }
                        ) {
                            Icon(
                                if(taskNotesExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = "Expand notes section"
                            )
                        }
                    else{
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth(0.15f)
                        )
                    }
                }

            }

        }
    })


}