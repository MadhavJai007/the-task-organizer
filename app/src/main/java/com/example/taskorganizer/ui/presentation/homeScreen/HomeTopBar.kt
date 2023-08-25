package com.example.taskorganizer.ui.presentation.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.taskorganizer.R
import com.example.taskorganizer.data.models.SortTypes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    navigateToAboutScreen:()-> Unit,
    homeViewModel: HomeViewModel,
    toggleSortBottomSheet: () -> Unit
) {
    var sortMenuExpanded by remember { mutableStateOf(false)}


    var selectedSort by remember { mutableStateOf(SortTypes.TITLE_ASC) }
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(R.string.app_title),
//                fontFamily = FontFamily(Font(R.font.playfair_display_regular)),
            )
        },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "logo",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
        },
        actions = {
            Row(

            ) {


                Box(){

                    IconButton(
                        onClick = {
//                            sortMenuExpanded = true
                            toggleSortBottomSheet()
                        }
                    ){
                        Icon(
                            imageVector = Icons.Default.Sort,
                            contentDescription = "Sorting dropdown menu"
                        )
                    }

                    // TODO: delete this
//                    DropdownMenu(
//                        expanded = sortMenuExpanded,
//                        onDismissRequest = { sortMenuExpanded = false },
//
//                    ) {
//                        SortTypes.values().forEachIndexed{index, sortOption ->
//                            DropdownMenuItem(
//                                text = {Text(text = sortOption.displayStr)},
//                                onClick = {
//                                    selectedSort = sortOption
//                                    sortMenuExpanded = false
//                                    homeViewModel.sortAndFilterTasks(sortOption)
//                                })
//                        }
//                    }
                }
                IconButton(onClick = {navigateToAboutScreen() }) {
                    Icon(
                        Icons.Default.Info, // id = R.drawable.about),
                        contentDescription = "about")
                }
            }

        }
    )
}