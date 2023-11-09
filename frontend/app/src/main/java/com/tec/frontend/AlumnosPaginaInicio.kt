package com.tec.frontend

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.ui.text.TextStyle
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.ui.theme.FrontendTheme

class AlumnosPaginaInicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaInicioAlum()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicioAlum() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF4169CF)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "¿List@ para jugar?",
                color = Color.White,
                style = TextStyle(fontSize = 30.sp),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 180.dp)
            )
            Box(
                modifier = Modifier
                    .width(821.dp)
                    .height(341.dp)
                    .background(Color.White)
                    .border(1.dp, Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Selecciona tu nombre",
                        color = Color.Black,
                        style = TextStyle(fontSize = 45.sp),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    var isExpaned by remember {
                        mutableStateOf(false)
                    }

                    var alumnos by remember {
                        mutableStateOf("")
                    }

                    ExposedDropdownMenuBox(
                        expanded = isExpaned,
                        onExpandedChange = { isExpaned = it }
                    ) {
                        TextField(
                            value = alumnos,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpaned)
                            },

                            colors = ExposedDropdownMenuDefaults.textFieldColors(),
                            modifier = Modifier.menuAnchor()
                        )
                        
                        ExposedDropdownMenu(expanded = isExpaned, onDismissRequest = { isExpaned = false }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(text = "Alumno 1")
                                },
                                onClick = {
                                    alumnos = "Alumno 1"
                                    isExpaned = false
                                }
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(70.dp))

                    val context = LocalContext.current
                    Button(
                        onClick = {
                            context.startActivity(Intent(context, SeleccionNivel::class.java))

                        },
                        modifier = Modifier
                            .border(2.dp, Orange, RoundedCornerShape(10.dp))
                            .width(264.dp) // Specify the width you desire
                            .height(45.dp), shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color.Transparent),



                        ) {
                        Text("Continuar",
                            color = Orange)
                    }

                    
                    /*listOf("Alumno 1", "Alumno 2").forEach { student ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(2.dp, Orange, RoundedCornerShape(10.dp))
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = student,
                                style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.Normal)
                            )


                        }
                    }*/
                }
            }
        }
    }
}