package com.tec.frontend

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.tec.frontend.Api.Images

class SharedViewModel: ViewModel() {
    companion object {
        val data = mutableStateListOf<Images>()
    }
}