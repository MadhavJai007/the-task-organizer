package com.example.taskorganizaer.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskorganizaer.ui.presentation.aboutScreen.AboutScreen
import com.example.taskorganizaer.ui.presentation.homeScreen.HomeScreen
import com.example.taskorganizaer.ui.presentation.updateNoteScreen.UpdateNoteScreen
import com.exmaple.taskorganizaer.ui.presentation.addNoteScreen.AddNoteScreen

@Composable
fun TaskApp(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = TaskAppScreen.Home.name,
    ) {
        composable(route = TaskAppScreen.Home.name) {
            HomeScreen(
                onFabClicked = { navController.navigate(TaskAppScreen.AddNotes.name) },
                navigateToUpdateNoteScreen = { noteId ->
                    navController.navigate("${TaskAppScreen.UpdateNotes.name}/$noteId")
                },
                navigateToAboutScreen = { navController.navigate(TaskAppScreen.About.name) }
            )
        }
        composable(
            route = "${TaskAppScreen.UpdateNotes.name}/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
            UpdateNoteScreen(
                noteId = noteId,
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(TaskAppScreen.AddNotes.name) {
            AddNoteScreen(navigateBack = { navController.popBackStack() })
        }
        composable(TaskAppScreen.About.name) {
            AboutScreen(navigateBack = { navController.popBackStack() })
        }
    }
}