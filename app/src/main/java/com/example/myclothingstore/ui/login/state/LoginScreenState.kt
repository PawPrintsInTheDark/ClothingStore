package com.example.myclothingstore.ui.login.state

sealed class LoginScreenEvent(){
    data class EmailChanged(val newEmail: String): LoginScreenEvent()
    data class PasswordChanged(val newPassword: String): LoginScreenEvent()
    data class ErrorStateChanged(val newError: String): LoginScreenEvent()
    data class ConfirmPasswordChanged(val newConfirmPassword: String): LoginScreenEvent()
}

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    var error: String = "",
    var confirmPassword: String = "",

)