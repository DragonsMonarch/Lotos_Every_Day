package com.dragonslotos.lotoseveryday

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dragonslotos.lotoseveryday.ui.Navigation.NavigationController
import com.dragonslotos.lotoseveryday.ui.Navigation.NavigatorDecorater
import com.dragonslotos.lotoseveryday.ui.theme.LotosTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.internal.DaggerCollections
import javax.inject.Inject

@AndroidEntryPoint

class MainActivity : ComponentActivity() {


    @Inject
    lateinit var navigatorDecorater: NavigatorDecorater

    //function for lock screen on orientation
    @Composable
    fun LockScreenOrientation(orientation: Int) {
        //get context
        val context = LocalContext.current

        //llock the screen once
        DisposableEffect(orientation) {
            //get activity
            val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
            //request sreen orientation
            val originalOrientation = activity.requestedOrientation
            //set orientation
            activity.requestedOrientation = orientation
            onDispose {
                // restore original orientation when view disappears
                activity.requestedOrientation = originalOrientation
            }
        }
    }

    fun Context.findActivity(): Activity? = when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LotosTheme {
                val navHostController = rememberNavController()
                navigatorDecorater.setController(navHostController)

                LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                NavigationController(navHostController)
            }

        }
    }
}