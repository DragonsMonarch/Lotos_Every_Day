package com.dragonslotos.lotoseveryday.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dragonslotos.lotoseveryday.ui.screens.Preview.PreivewScreen
import com.dragonslotos.lotoseveryday.ui.screens.Preview.PreviewViewModel
import com.dragonslotos.lotoseveryday.ui.screens.Welcome.WelcomeScreen

@Composable
fun NavigationController (
    navController: NavHostController
) {
    NavHost(
    navController = navController,
    startDestination = "preview"
    ) {
        composable("preview"){ PreivewScreen().show(navController = navController) }
        composable("welcome"){ WelcomeScreen(navController) }
    }
}