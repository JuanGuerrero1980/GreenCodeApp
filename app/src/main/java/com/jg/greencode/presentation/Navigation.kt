package com.jg.greencode.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jg.greencode.presentation.detail.DetailScreen
import com.jg.greencode.presentation.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(onConversionClick = { id ->
                navController.navigate(Screen.Detail.createRoute(id))
            })
        }
        composable(Screen.Detail.route) { backStackEntry ->
            val conversionId = backStackEntry.arguments?.getString("conversionId")?.toIntOrNull() ?: return@composable
            DetailScreen(conversionId = conversionId, onBack = { navController.popBackStack() })
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{conversionId}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}