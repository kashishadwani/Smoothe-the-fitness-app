package com.example.smoothe.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.room.util.copy
import com.example.smoothe.data.rules.Validator

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.java.simpleName

var RegistrationUIState = mutableStateOf(RegistrationUIState())

    fun onEvent(event: UIEvent){
        validatedatawithrules()
        when(event){
            is UIEvent.FirstNameChange ->{
                RegistrationUIState.value = RegistrationUIState.value.copy(
                    firstName = event.firstName
                )
                printstate()
            }
            is UIEvent.LastNameChange ->{
                RegistrationUIState.value = RegistrationUIState.value.copy(
                    lastName = event.lastName
                )
                printstate()
            }
            is UIEvent.EmailChange ->{
                RegistrationUIState.value = RegistrationUIState.value.copy(
                    email = event.email
                )
                printstate()
            }
            is UIEvent.PasswordChange ->{
                RegistrationUIState.value = RegistrationUIState.value.copy(
                    password = event.password
                )
                printstate()
            }
            is UIEvent.RegisterButtonClicked ->{
                signUp()
            }
        }
    }

    private fun signUp() {
        Log.d(TAG,"Inside_signUp")
        printstate()

        validatedatawithrules()
    }

    private fun printstate(){
        Log.d(TAG,"Inside_printstate")
        Log.d(TAG, RegistrationUIState.value.toString())
    }

    private fun validatedatawithrules() {
        val fnameresult = Validator.ValidateFirstName(
            firstName = RegistrationUIState.value.firstName
        )

        val lNameResult = Validator.ValidateLastName(
            lastName = RegistrationUIState.value.lastName
        )

        val emailResult = Validator.ValidateEmail(
            email = RegistrationUIState.value.email
        )

        val passwordResult = Validator.ValidatePassword(
            password = RegistrationUIState.value.password
        )

        Log.d(TAG,"Inside_validatedatawithrules")
        Log.d(TAG,"fnameresult= $fnameresult")
        Log.d(TAG,"lNameResult= $lNameResult")
        Log.d(TAG,"emailResult= $emailResult")
        Log.d(TAG,"passwordResult= $passwordResult")

        RegistrationUIState.value = RegistrationUIState.value.copy(
            firstNameError = fnameresult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )
    }
}