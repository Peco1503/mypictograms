package com.tec.frontend

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.Api.Alumno
import com.tec.frontend.Api.RetrofitInstance
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val Orange1 = Color(0xFFEE6B11)

class DashboardProfe : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    dashboard()
                }
            }
        }
    }
}

@Composable
fun dashboard() {
    var alumnos by remember { mutableStateOf<List<Alumno>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit){
        coroutineScope.launch {
            try {
                // Make Retrofit API call on the background thread
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.apiService.infoAlumno()
                }

                // Assuming response contains an "id" and "type" field
                alumnos = response

            } catch (e: Exception) {
                // Handle error
                // You can display an error message or perform other actions
                Log.d(ContentValues.TAG, e.toString())
            }
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF4169CF)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Hola de nuevo, Laura!",
                style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.Normal),
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .width(950.dp)
                    .height(446.dp)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(30.dp),
                        text = "Mis alumnos",
                        style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.Bold),
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp)
                    ) {
                        alumnos.forEach { alumno ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                alumno.name?.let { Text(text = it, style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.Normal)) }
                                val context = LocalContext.current
                                Button(
                                    onClick = {
                                        val intent = Intent(context, InfoAlumno::class.java)
                                        intent.putExtra("alumnoId", alumno.id) // Reemplaza "alumnoId" con la clave que desees
                                        context.startActivity(intent)
                                              },
                                    modifier = Modifier
                                        .border(2.dp, Orange1, RoundedCornerShape(10.dp))
                                        .width(80.dp)
                                        .height(45.dp),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(Orange1)
                                ) {
                                    Text("Info", color = Color.White)
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center // Center the buttons
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Nuevo: ", color = Color.White, style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Normal))
                    Spacer(modifier = Modifier.width(16.dp))
                    val context = LocalContext.current

                    Button(
                        onClick = { context.startActivity(Intent(context, NuevoAlumno::class.java)) },
                        modifier = Modifier
                            .border(2.dp, Orange1, RoundedCornerShape(10.dp))
                            .width(125.dp)
                            .height(44.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Orange1)
                    ) {
                        Text("Alumn@", color = Color.White, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal))
                    }

                    val context1 = LocalContext.current
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {context1.startActivity(Intent(context1, Registro::class.java)) },
                        modifier = Modifier
                            .border(2.dp, Orange1, RoundedCornerShape(10.dp))
                            .width(125.dp)
                            .height(44.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Orange1)
                    ) {
                        Text("Admin", color = Color.White, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal))
                    }
                }
            }
        }
    }
}