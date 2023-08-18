package com.vixiloc.vixitask.domain.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vixiloc.vixitask.presentations.screens.AddScreen
import com.vixiloc.vixitask.presentations.screens.DetailScreen
import com.vixiloc.vixitask.presentations.screens.HomeScreen
import com.vixiloc.vixitask.presentations.screens.SplashScreen
import com.vixiloc.vixitask.presentations.screens.UpdateScreen

@Composable
fun MainNavigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = MainDestination.Splash.route) {
        composable(route = MainDestination.Splash.route) {
            SplashScreen(navHostController = navHostController)
        }
        composable(route = MainDestination.Home.route) {
            HomeScreen(navHostController = navHostController)
        }
        composable(route = MainDestination.Detail.route) {
            DetailScreen(navHostController = navHostController)
        }
        composable(route = MainDestination.Add.route) {
            AddScreen(navHostController = navHostController)
        }
        composable(route = MainDestination.Update.route) {
            UpdateScreen(navHostController = navHostController)
        }
    }
}

sealed class MainDestination(val route: String) {
    object Splash : MainDestination(route = "splash")
    object Home : MainDestination(route = "home")
    object Detail : MainDestination(route = "detail")
    object Add : MainDestination(route = "add")
    object Update : MainDestination(route = "update")
}