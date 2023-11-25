package com.tec.frontend

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tec.frontend.Api.Alumno
import com.tec.frontend.Api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class RegistrationState {
    object Loading : RegistrationState()
    data class Success1(val response: Void) : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}

class RegistroViewModel : ViewModel() {
    private val _registrationState: MutableStateFlow<RegistrationState> = MutableStateFlow(RegistrationState.Loading)
    val registrationState: StateFlow<RegistrationState> get() = _registrationState
}
