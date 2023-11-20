package com.chillieman.chilliekeeper.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chillieman.chilliekeeper.ui.Destinations.KEEPER_MAIN
import com.chillieman.chilliekeeper.ui.Destinations.SIGN_IN_ROUTE
import com.chillieman.chilliekeeper.ui.main.MainRoute
import com.chillieman.chilliekeeper.ui.signin.SignInRoute

object Destinations {
    const val SIGN_IN_ROUTE = "signin"
    const val KEEPER_MAIN = "main"
}

@Composable
fun KeeperNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = SIGN_IN_ROUTE,
    ) {
        composable(SIGN_IN_ROUTE) {
            SignInRoute(
                onSignInSuccess = {
                    navController.navigate(KEEPER_MAIN)
                },
                onNavUp = navController::navigateUp,
            )
        }

        composable(KEEPER_MAIN) {
            MainRoute()
        }
    }
}
