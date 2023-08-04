package com.example.taskorganizaer.ui.presentation.homeScreen

import android.graphics.fonts.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.example.taskorganizaer.R
import com.example.taskorganizaer.data.models.SortTypes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    navigateToAboutScreen:()-> Unit,
    homeViewModel: HomeViewModel
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
//                    Text(
//                        text = selectedSort.name,
//                        modifier = Modifier
////                            .fillMaxSize()
////                            .clickable(
////
////                            )
//                    )
                    IconButton(
                        onClick = {
                            sortMenuExpanded = true
                        }
                    ){
                        Icon(
                            imageVector = Icons.Default.Sort,
                            contentDescription = "Sorting dropdown menu"
                        )
                    }

                    DropdownMenu(
                        expanded = sortMenuExpanded,
                        onDismissRequest = { sortMenuExpanded = false },

                    ) {
                        SortTypes.values().forEachIndexed{index, sortOption ->
                            DropdownMenuItem(
                                text = {Text(text = sortOption.displayStr)},
                                onClick = {
                                    selectedSort = sortOption
                                    sortMenuExpanded = false
                                    homeViewModel.sortTasks(sortOption)
                                })
                        }
                    }
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