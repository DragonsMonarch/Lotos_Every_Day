package com.dragonslotos.lotoseveryday.ui.Navigation

import android.os.Build
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dragonslotos.lotoseveryday.ui.screens.Preview.PreivewScreen
import com.dragonslotos.lotoseveryday.ui.screens.Welcome.WelcomeScreen
import java.io.Serializable
import javax.inject.Singleton


//NavigatorDecorater for hilt use and example bundle use
@Singleton
class NavigatorDecorater {

    private var navController: NavHostController? = null


    inline fun <reified T : Serializable> getSerializeble(key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) getpreviousArguments()?.getSerializable(key, T::class.java)
        else getpreviousArguments()?.getSerializable(key) as? T
    }


    fun setController(controller: NavHostController) {
        navController = controller
    }
    fun saveSerializable(key: String, data: java.io.Serializable){
        navController!!.currentBackStackEntry?.arguments?.apply {
            putSerializable(key, data)
        }
        navController!!.navigate("welcome")
    }


    fun getpreviousArguments():Bundle? {
        return navController!!.previousBackStackEntry?.arguments
    }

    fun clear() {
        navController = null
    }

    fun navigate(route:String) {
        navController!!.navigate(route)
    }
}

@Composable
fun NavigationController (
    navController: NavHostController) {
    val navigation = NavigatorDecorater()
    navigation.setController(navController)

    NavHost(
    navController = navController,
    startDestination = "preview"
    ) {
        composable("preview"){ PreivewScreen(navigation ,hiltViewModel()).show() }
        composable("welcome"){ WelcomeScreen(hiltViewModel()).show() }
    }
}