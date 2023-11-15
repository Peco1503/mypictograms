package com.tec.frontend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tec.frontend.Api.registerRequest
import com.tec.frontend.Api.registerResponse
import com.tec.frontend.Api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class RegistrationState {
    object Loading : RegistrationState()
    data class Success(val response: registerResponse) : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}

class RegistroViewModel : ViewModel() {

    private val _registrationState: MutableStateFlow<RegistrationState> = MutableStateFlow(RegistrationState.Loading)
    val registrationState: StateFlow<RegistrationState> get() = _registrationState

    fun registerUser(user: String, password: String, userType: String) {
        viewModelScope.launch {
            try {
                _registrationState.value = RegistrationState.Loading

                val response: Response<registerResponse> = if (userType == "admin") {
                    RetrofitInstance.apiService.createAdmin(registerRequest(user, password, userType))
                } else {
                    RetrofitInstance.apiService.createParent(registerRequest(user, password, userType))
                }

                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    _registrationState.value = RegistrationState.Success(registerResponse!!)
                } else {
                    _registrationState.value = RegistrationState.Error("Registration failed")
                }
            } catch (e: Exception) {
                _registrationState.value = RegistrationState.Error("An error occurred")
            }
        }
    }
}
