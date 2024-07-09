package com.example.smoothe.Navigation

import android.app.Activity
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smoothe.Screens.HomeScreen
import com.example.smoothe.Screens.LoginScreen
import com.example.smoothe.Screens.SignupScreen
import com.example.smoothe.Screens.Termsandconditionsscreen

sealed class Screen{
    object LoginScreen: Screen()
    object SignupScreen: Screen()
    object termsandconditionsfile: Screen()
    object HomeScreen: Screen()

}

object SmootheRouter{
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoginScreen)

    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }

}

