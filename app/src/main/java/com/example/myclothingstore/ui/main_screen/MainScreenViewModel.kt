package com.example.myclothingstore.ui.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {
    var textState by mutableStateOf("")
        private set

    fun onTextChange(newText: String) {
        textState = newText
    }
}