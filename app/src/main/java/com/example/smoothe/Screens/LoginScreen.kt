package com.example.smoothe.Screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smoothe.Navigation.Screen
import com.example.smoothe.Navigation.SmootheRouter
import com.example.smoothe.Navigation.SystemBackButtonHandler
import com.example.smoothe.R

@Composable
fun LoginScreen(){
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            NormaltextComponent(value = stringResource(id = R.string.login))
            HeadingtextComponent(value = stringResource(id = R.string.welcome_back))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextField(labelvalue = stringResource(id = R.string.email),
                imageVector = Icons.Outlined.Email)
            PasswordTextField(labelvalue = stringResource(id = R.string.password),
                imageVector = Icons.Outlined.Lock
            )
            Spacer(modifier = Modifier.height(40.dp))
            UnderlinedtextComponent(value = stringResource(id = R.string.forgot_password))
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent(value = stringResource(id = R.string.login))
            DividerTextComponent()
            ClickableLoginTextComponent ( tryingtoLogin = false, onTextSelected = {
                SmootheRouter.navigateTo(Screen.SignupScreen)
            })
        }

    }
    SystemBackButtonHandler {
        SmootheRouter.navigateTo(Screen.SignupScreen)
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}
@Composable
fun UnderlinedtextComponent(value:String){
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
        ,color = colorResource(id = R.color.colorGray),
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}