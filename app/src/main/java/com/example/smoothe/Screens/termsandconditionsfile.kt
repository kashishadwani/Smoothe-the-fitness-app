package com.example.smoothe.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.smoothe.Navigation.Screen
import com.example.smoothe.Navigation.SmootheRouter
import com.example.smoothe.Navigation.SystemBackButtonHandler
import com.example.smoothe.R
import com.example.smoothe.app.SmootheApp

@Composable
fun Termsandconditionsscreen(){
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)) {
        HeadingtextComponent(value = stringResource(id = R.string.terms_and_conditions_header))
    }
    SystemBackButtonHandler {
        SmootheRouter.navigateTo(Screen.SignupScreen)
    }
}