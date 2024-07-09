package com.example.smoothe.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.smoothe.data.rules.Validator
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class signupViewModel @Inject constructor() : ViewModel() {

    private val TAG = signupViewModel::class.java.simpleName

    var RegistrationUIState = mutableStateOf(RegistrationUIState())

    var allvalidationspassed = mutableStateOf(false)

    var signupinprogress = mutableStateOf(false)

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
            is UIEvent.PrivacyPolicyCheckBoxClicked ->{
                RegistrationUIState.value = RegistrationUIState.value.copy(
                    privacyerror = event.status
                )
                printstate()
            }

            is UIEvent.EmailChange -> TODO()
            is UIEvent.FirstNameChange -> TODO()
            is UIEvent.LastNameChange -> TODO()
            is UIEvent.PasswordChange -> TODO()
            is UIEvent.PrivacyPolicyCheckBoxClicked -> TODO()
            UIEvent.RegisterButtonClicked -> TODO()
        }
    }

    fun signUp() {
        Log.d(TAG,"Inside_signUp")
        printstate()

        createUserInFirebase(
            email= RegistrationUIState.value.email,
            password = RegistrationUIState.value.password
        )
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

        val privacypolicyresult= Validator.ValidatePrivacyPolicyAcceptance(
            statusvalue = RegistrationUIState.value.privacyPolicyAccepted
        )

        Log.d(TAG,"Inside_validatedatawithrules")
        Log.d(TAG,"fnameresult= $fnameresult")
        Log.d(TAG,"lNameResult= $lNameResult")
        Log.d(TAG,"emailResult= $emailResult")
        Log.d(TAG,"passwordResult= $passwordResult")
        Log.d(TAG,"privacypolicyresult= $privacypolicyresult")

        RegistrationUIState.value = RegistrationUIState.value.copy(
            firstNameError = fnameresult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyerror = privacypolicyresult.status
        )

        if(fnameresult.status && lNameResult.status && emailResult.status && passwordResult.status){
            allvalidationspassed.value = true
        }else{
            allvalidationspassed.value = false
        }
    }
    private fun createUserInFirebase(email : String, password:String) {
        signupinprogress.value = true
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                Log.d(TAG,"Inside_OnCompleteListener")
                Log.d(TAG,"isSuccessful = ${it.isSuccessful}")

                signupinprogress.value = false
                if(it.isSuccessful){

                }
            }
            .addOnFailureListener{
                Log.d(TAG,"Inside_OnFailureListener")
                Log.d(TAG,"Exception = ${it.message}")
            }
    }
}