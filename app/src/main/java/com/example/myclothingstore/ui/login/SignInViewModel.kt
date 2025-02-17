package com.example.myclothingstore.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myclothingstore.ui.login.state.LoginScreenEvent
import com.example.myclothingstore.ui.login.state.LoginScreenState

class SignInViewModel : ViewModel() {
    var state by mutableStateOf(LoginScreenState())
        private set

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.EmailChanged -> this.state = state.copy(email = event.newEmail)
            is LoginScreenEvent.PasswordChanged -> this.state = state.copy(password = event.newPassword)
            is LoginScreenEvent.ErrorStateChanged -> this.state = state.copy(error =  event.newError)
            is LoginScreenEvent.ConfirmPasswordChanged -> this.state = state.copy(confirmPassword = event.newConfirmPassword)
        }
    }
}