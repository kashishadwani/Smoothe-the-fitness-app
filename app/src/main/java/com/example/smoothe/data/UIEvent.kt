package com.example.smoothe.data

sealed class UIEvent {
    data class FirstNameChange(val firstName: String): UIEvent()
    data class LastNameChange(val lastName: String): UIEvent()
    data class EmailChange(val email: String): UIEvent()
    data class PasswordChange(val password: String): UIEvent()

    data class PrivacyPolicyCheckBoxClicked(val status:Boolean): UIEvent()
    object RegisterButtonClicked : UIEvent()

}