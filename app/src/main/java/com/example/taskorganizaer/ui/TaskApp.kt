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
import com.example.taskorganizaer.ui.presentation.updateTaskScreen.UpdateTaskScreen
import com.exmaple.taskorganizaer.ui.presentation.addNoteScreen.AddTaskScreen

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
                onFabClicked = { navController.navigate(TaskAppScreen.AddTasks.name) },
                navigateToUpdateTaskScreen = { taskId ->
                    navController.navigate("${TaskAppScreen.UpdateTasks.name}/$taskId")
                },
                navigateToAboutScreen = { navController.navigate(TaskAppScreen.About.name) }
            )
        }
        composable(
            route = "${TaskAppScreen.UpdateTasks.name}/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0
            UpdateTaskScreen(
                taskId = taskId,
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(TaskAppScreen.AddTasks.name) {
            AddTaskScreen(navigateBack = { navController.popBackStack() })
        }
        composable(TaskAppScreen.About.name) {
            AboutScreen(navigateBack = { navController.popBackStack() })
        }
    }
}