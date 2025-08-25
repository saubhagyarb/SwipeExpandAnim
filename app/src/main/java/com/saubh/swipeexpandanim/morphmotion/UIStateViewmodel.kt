package com.saubh.swipeexpandanim.morphmotion

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UIStateViewmodel : ViewModel() {

    // Private mutable flow
    private val _motionContentJson = MutableStateFlow("")
    // Public immutable flow
    val motionContentJson: StateFlow<String> = _motionContentJson

    fun getJsonUiData() {
        viewModelScope.launch(Dispatchers.Default) {
            _motionContentJson.value = json // assign your JSON string
        }
    }
}
