package com.example.smoothe.data

sealed class loginUIEvent {
    data class EmailChange(val email: String): loginUIEvent()
    data class PasswordChange(val password: String): loginUIEvent()
    object LoginButtonClicked : loginUIEvent()

}