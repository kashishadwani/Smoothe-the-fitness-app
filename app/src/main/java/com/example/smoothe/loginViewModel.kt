package com.example.smoothe

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.smoothe.Navigation.Screen
import com.example.smoothe.Navigation.SmootheRouter
import com.example.smoothe.data.LoginUIState
import com.example.smoothe.data.loginUIEvent
import com.example.smoothe.data.rules.Validator
import com.google.firebase.auth.FirebaseAuth

class loginViewModel : ViewModel(){

    private val TAG = loginViewModel::class.java.simpleName

    var allvalidationspassed = mutableStateOf(false)

    var loginUIState = mutableStateOf(LoginUIState())

    var logininprogress = mutableStateOf(false)


    fun onEvent(event: loginUIEvent){
        when(event){
            is loginUIEvent.EmailChange ->{
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }
            is loginUIEvent.PasswordChange ->{
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }
            is loginUIEvent.LoginButtonClicked ->{
                login()

            }

        }
        validateLoginUIDataWithRules()
    }

    private fun login() {
        logininprogress.value = true

        val email = loginUIState.value.email
        val password = loginUIState.value.password

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                Log.d(TAG,"Inside_login_success")
                Log.d(TAG,"${it.isSuccessful}")

                if(it.isSuccessful){
                    logininprogress.value = false
                    SmootheRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener{
                Log.d(TAG,"Inside_login_failure")
                Log.d(TAG,"${it.localizedMessage}")

                logininprogress.value = false

            }
    }

    private fun validateLoginUIDataWithRules(){
        val emailResult = Validator.ValidateEmail(
            email = loginUIState.value.email
        )
        val passwordResult = Validator.ValidatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
           emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allvalidationspassed.value = emailResult.status && passwordResult.status
    }
}