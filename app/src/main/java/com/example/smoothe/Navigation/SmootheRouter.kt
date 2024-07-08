package com.example.smoothe.Navigation

import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

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