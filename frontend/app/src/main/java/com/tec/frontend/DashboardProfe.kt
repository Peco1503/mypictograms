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
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
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
    private var AdminId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                AdminId = intent.getIntExtra("AdminID", -1)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    dashboard(AdminId)
                    BB()
                }
            }
        }
    }
}

@Composable
fun dashboard(Adminid: Int) {
    val scrollState = rememberScrollState()
    var alumnos by remember { mutableStateOf<List<Alumno>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                // Make Retrofit API call on the background thread
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.apiService.infoAlumno(AdminID = Adminid)
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
                modifier = Modifier.padding(bottom=20.dp),
                text = "Hola de nuevo, terapeuta!",
                style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.Normal),
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .width(950.dp)
                    .height(465.dp)
                    .background(Color.White)
                    .verticalScroll(scrollState)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 15.dp),
                        text = "Mis alumnos",
                        style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold),
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
                            ) {
                                alumno.name?.let {
                                    Text(
                                        modifier = Modifier
                                            .border(
                                                2.dp,
                                                Color.Gray,
                                                RoundedCornerShape(0.dp)
                                            ).padding(8.dp).width(750.dp),
                                        text = it,
                                        style = TextStyle(
                                            fontSize = 35.sp,
                                            fontWeight = FontWeight.Normal
                                        )
                                    )
                                }
                                val context = LocalContext.current
                                Button(
                                    modifier = Modifier.padding(start=8.dp),
                                    onClick = {
                                        val intent = Intent(context, InfoAlumno::class.java)
                                        intent.putExtra(
                                            "alumnoId",
                                            alumno.id
                                        ) // Reemplaza "alumnoId" con la clave que desees
                                        intent.putExtra("AdminID", Adminid)
                                        context.startActivity(intent)
                                    },
                                    shape = RoundedCornerShape(0.dp),
                                    colors = ButtonDefaults.buttonColors(Orange1)
                                ) {
                                    Text("Info", color = Color.White, fontSize = 35.sp)
                                }
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=20.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Nuevo: ",
                        color = Color.White,
                        style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.Normal)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    val context = LocalContext.current

                    Button(
                        onClick = {
                            val intent = Intent(context, NuevoAlumno::class.java)
                            intent.putExtra("AdminID", Adminid)
                            context.startActivity(intent)
                        },
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(Orange1)
                    ) {
                        Text(
                            "Alumno",
                            color = Color.White,
                            style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.Normal)
                        )
                    }

                    val context1 = LocalContext.current
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {
                            context1.startActivity(Intent(context1, Registro::class.java))
                        },
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(Orange1)
                    ) {
                        Text(
                            "Administrador",
                            color = Color.White,
                            style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.Normal)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BB() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        val context = LocalContext.current
        Button(
            shape = RectangleShape,
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        InicioSesion::class.java
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(Orange)
        ) {
            Text(
                "Atrás",
                style = TextStyle(fontSize = 35.sp)
            )
        }
    }
}
