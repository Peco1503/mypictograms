package com.tec.frontend

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tec.frontend.Api.Alumno
import com.tec.frontend.Api.RetrofitInstance
import com.tec.frontend.Api.registerRequest
import com.tec.frontend.Api.registerResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class RegistrationState {
    object Loading : RegistrationState()
    data class Success(val response: registerResponse) : RegistrationState()
    data class Success1(val response: Alumno) : RegistrationState()
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

    fun registerAlumno(name: String, birthYear: Int, gender: String, idTutor: Int?, maximumMinigameLevel: Int, description: String, cognitiveLevel: String) {
        viewModelScope.launch {
            try {
                _registrationState.value = RegistrationState.Loading
                val response : Response<Alumno> = RetrofitInstance.apiService.insertalumno(Alumno(null, name, birthYear, gender, idTutor, maximumMinigameLevel, description, cognitiveLevel, 1))
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    _registrationState.value = RegistrationState.Success1(registerResponse!!)
                } else {
                    _registrationState.value = RegistrationState.Error("Registration failed")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error durante la llamada a la API", e)
            }
        }
    }

    fun updateAlumno(
        alumnoId: Int, // ID del alumno que se va a actualizar
        name: String,
        birthYear: Int,
        gender: String,
        idTutor: Int?,
        maximumMinigameLevel: Int,
        description: String,
        cognitiveLevel: String,
        therapistId: Int
    ) {
        viewModelScope.launch {
            try {
                _registrationState.value = RegistrationState.Loading

                // Crear un objeto Alumno con los datos actualizados
                val updatedAlumno = Alumno(
                    id = alumnoId,
                    name = name,
                    birthYear = birthYear,
                    gender = gender,
                    parentId = idTutor,
                    maximumMinigameLevel = maximumMinigameLevel,
                    description = description,
                    cognitiveLevel = cognitiveLevel,
                    therapistId = therapistId
                )

                // Realizar la solicitud de actualización al servidor
                val response: Response<Alumno> = RetrofitInstance.apiService.actualizarAlumno(alumnoId = alumnoId, updatedAlumno)

                if (response.isSuccessful) {
                    val updatedResponse = response.body()
                    _registrationState.value = RegistrationState.Success1(updatedResponse!!)
                } else {
                    _registrationState.value = RegistrationState.Error("Update failed")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error during API call", e)
            }
        }
    }


}
