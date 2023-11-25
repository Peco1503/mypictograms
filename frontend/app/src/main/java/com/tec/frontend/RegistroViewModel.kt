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
    data class Success1(val response: Alumno) : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}

class RegistroViewModel : ViewModel() {

    private val _registrationState: MutableStateFlow<RegistrationState> = MutableStateFlow(RegistrationState.Loading)
    val registrationState: StateFlow<RegistrationState> get() = _registrationState

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
