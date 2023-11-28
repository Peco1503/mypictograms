package com.tec.frontend

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.Api.Alumno
import com.tec.frontend.Api.RetrofitInstance
import com.tec.frontend.Api.Terapeuta
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PerfilAlumnoPadre : ComponentActivity() {
    private var EstudianteId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                EstudianteId = intent.getIntExtra("alumnoId", -1)
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    PerfilAlumnoPadres(EstudianteId ,activityContext = this)
                }
            }
        }
    }
}

@Composable
// @Preview(name = "Landscape Mode", showBackground = true, device = Devices.PIXEL_C, widthDp = 1280)
fun PerfilAlumnoPadres(EstudianteId: Int, activityContext: ComponentActivity) {
    val coroutineScope = rememberCoroutineScope()
    var Estudiante by remember { mutableStateOf(Alumno()) }
    var Terapia by remember { mutableStateOf<List<Terapeuta>>(emptyList()) }
    var NombreTerapeuta by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                // Make Retrofit API call on the background thread
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.apiService.getEstudiante(alumnoId = EstudianteId)
                }

                // Actualizar el estado del estudiante con la respuesta de la API
                Estudiante = response[0]

                val response2 = withContext(Dispatchers.IO){
                    RetrofitInstance.apiService.getTerapia()
                }

                Terapia = response2
            } catch (e: Exception) {
                // Handle error
                // You can display an error message or perform other actions
                Log.e("InfoAlumno", "Error al obtener información del estudiante", e)
            }
        }
    }
    Terapia.forEach { admin->
        if(admin.id == Estudiante.therapistId){
            NombreTerapeuta = admin.user
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(), color = Color(0xFF4169CF)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(770.dp)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(50.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${Estudiante.name}",
                        style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold),
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Row(modifier = Modifier.padding(top = 30.dp)) {
                        Text(
                            text = "Año de nacimiento:",
                            fontWeight = FontWeight.Medium,
                            fontSize = 35.sp,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(text = "${Estudiante.birthYear}", fontSize = 35.sp)
                    }
                    Row(modifier = Modifier.padding(top = 20.dp)) {
                        Text(
                            text = "Género:",
                            fontWeight = FontWeight.Medium,
                            fontSize = 35.sp,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(text = "${Estudiante.gender}", fontSize = 35.sp)
                    }
                    Row(modifier = Modifier.padding(top = 20.dp)) {
                        Text(
                            text = "Terapeuta:",
                            fontWeight = FontWeight.Medium,
                            fontSize = 35.sp,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        var NTerapeuta = NombreTerapeuta.take(25)
                        Text(text = "$NTerapeuta...", fontSize = 35.sp)
                    }
                    Button(shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(Orange),
                        onClick = {
                            activityContext.finish()
                        },
                        modifier = Modifier.padding(top=30.dp)
                    ) {
                        Text(text = "Atrás", fontSize = 35.sp)
                    }
                }
            }
        }
    }
}
