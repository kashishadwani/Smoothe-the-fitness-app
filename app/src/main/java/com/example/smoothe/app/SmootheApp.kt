package com.example.smoothe.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smoothe.HomeViewModel
import com.example.smoothe.Navigation.Screen
import com.example.smoothe.Navigation.SmootheRouter
import com.example.smoothe.ui.theme.SmootheTheme

@Composable
fun SmootheApp(homeViewModel: HomeViewModel = viewModel()){

    homeViewModel.checkForActiveSession()


    SmootheTheme{

        if (homeViewModel.isUserLoggedIn.value==true){
            SmootheRouter.navigateTo(Screen.HomeScreen)
        }
        Crossfade(targetState = SmootheRouter.currentScreen) {
            currentState-> when (currentState.value){
                is Screen.SignupScreen -> {
                    Screen.SignupScreen
                }
                is Screen.termsandconditionsfile ->{
                    Screen.termsandconditionsfile
                }
                is Screen.LoginScreen ->{
                    Screen.LoginScreen
                }
                is Screen.HomeScreen ->{
                    Screen.HomeScreen
                }
            }
        }
    }
}