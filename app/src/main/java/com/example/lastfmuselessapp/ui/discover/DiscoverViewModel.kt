package com.example.lastfmuselessapp.ui.discover

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DiscoverViewModel @Inject constructor() : ViewModel() {

    var searchText: MutableState<String> = mutableStateOf("")
        private set

    var focused: MutableState<Boolean> = mutableStateOf(false)

    fun onSearchTextCleared() {
        searchText.value = ""
    }

    fun onSearchTextChanged(newText: String) {
        searchText.value = newText
    }

    fun onFocusChanged(focused: Boolean) {
        this.focused.value = focused
        if (!focused) {
            onSearchTextCleared()
        }
    }
}