package com.tec.frontend

import android.content.Intent
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.Api.Alumno
import com.tec.frontend.Api.RetrofitInstance
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class InfoAlumno : ComponentActivity() {
    private var EstudianteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener el valor del ID del alumno del intent
        EstudianteId = intent.getIntExtra("alumnoId", -1)

        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    infoAlumno(EstudianteId = EstudianteId)
                }
            }
        }
    }
}


@Composable
//@Preview(name = "Landscape Mode", showBackground = true, device = Devices.PIXEL_C, widthDp = 1280)
fun infoAlumno(EstudianteId: Int) {
    val coroutineScope = rememberCoroutineScope()
    var Estudiante by remember { mutableStateOf(Alumno()) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                // Make Retrofit API call on the background thread
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.apiService.getEstudiante(alumnoId = EstudianteId)
                }

                // Actualizar el estado del estudiante con la respuesta de la API
                Estudiante = response[0]

            } catch (e: Exception) {
                // Handle error
                // You can display an error message or perform other actions
                Log.e("InfoAlumno", "Error al obtener información del estudiante", e)
            }
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
                    .width(900.dp)
                    .background(Color.White)
                    .padding(50.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${Estudiante.name}",
                        style = TextStyle(
                            fontSize = 50.sp, fontWeight = FontWeight.Bold
                        ),
                    )
                    Row(modifier = Modifier.padding(top = 30.dp)) {
                        Text(
                            text = "Año de nacimiento:",
                            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 35.sp),
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(text = "${Estudiante.birthYear}", style = TextStyle(fontSize = 35.sp))
                    }
                    Row(modifier = Modifier.padding(top = 20.dp)) {
                        Text(
                            text = "Género:",
                            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 35.sp),
                            modifier = Modifier.padding(end = 10.dp)

                        )
                        Text(text = "${Estudiante.gender}", style = TextStyle(fontSize = 35.sp))
                    }
                    Row(modifier = Modifier.padding(top = 20.dp)) {
                        Text(
                            text = "Tutor:",
                            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 35.sp),
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(text = "${Estudiante.parentId}", style = TextStyle(fontSize = 35.sp))
                    }
                    Row(modifier = Modifier.padding(top = 20.dp)) {
                        Text(
                            text = "Nivel Autorizado:",
                            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 35.sp),
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(
                            text = "${Estudiante.maximumMinigameLevel}",
                            style = TextStyle(fontSize = 35.sp)
                        )
                    }
                    Row(modifier = Modifier.padding(top = 20.dp)) {
                        Text(
                            text = "Descripción:",
                            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 35.sp),
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(
                            text = "${Estudiante.description}", style = TextStyle(fontSize = 35.sp)
                        )
                    }
                    Row(modifier = Modifier.padding(top = 20.dp)) {
                        Text(
                            text = "Nivel Cognitivo:",
                            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 35.sp),
                            modifier = Modifier.padding(end = 10.dp)

                        )
                        Text(
                            text = "${Estudiante.cognitiveLevel}",
                            style = TextStyle(fontSize = 35.sp)
                        )
                    }
                    val context1 = LocalContext.current
                    Row(
                        modifier = Modifier.padding(top = 40.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                val intent = Intent(context1, DashboardProfe::class.java)
                                intent.putExtra("AdminID", Estudiante.therapistId)
                                context1.startActivity(intent)
                            },
                            shape = RoundedCornerShape(0.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                            modifier = Modifier.padding(end = 20.dp)
                        ) {
                            Text(text = "Atrás", style = TextStyle(fontSize = 35.sp))
                        }
                        Button(
                            onClick = {
                                val intent = Intent(context1, EditAlumno::class.java)
                                intent.putExtra("alumnoId", EstudianteId)
                                intent.putExtra("Nivel", Estudiante.maximumMinigameLevel)
                                context1.startActivity(intent)
                            },
                            shape = RoundedCornerShape(0.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        ) {
                            Text(text = "Editar", style = TextStyle(fontSize = 35.sp))
                        }
                    }
                }
            }
        }

    }
}



