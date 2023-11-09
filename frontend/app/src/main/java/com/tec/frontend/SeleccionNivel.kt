package com.tec.frontend

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.ui.theme.FrontendTheme

class SeleccionNivel : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NivelS()
                }
            }
        }
    }
}

@Composable
fun NivelS() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF4169CF)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(444.dp)
                    .height(450.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center // Center content inside the Box
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Selecciona el Nivel",
                        color = Color.Black,
                        style = TextStyle(fontSize = 30.sp),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    val context1 = LocalContext.current
                    Button( //1
                        onClick = {
                            context1.startActivity(
                                Intent(
                                    context1,
                                    AlumnosPaginaInicio::class.java
                                )
                            )
                        },
                        modifier = Modifier

                            .width(300.dp) // Specify the width you desire
                            .height(60.dp), shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(Orange)

                    ) {
                        Text(
                            "Nivel 1",
                            style = TextStyle(fontSize = 30.sp)
                        )

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    val context2 = LocalContext.current
                    Button( //2
                        onClick = {
                            context2.startActivity(
                                Intent(
                                    context2,
                                    AlumnosPaginaInicio::class.java
                                )
                            )
                        },
                        modifier = Modifier
                            .width(300.dp) // Specify the width you desire
                            .height(60.dp), shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(Orange)

                    ) {
                        Text(
                            "Nivel 2",
                            style = TextStyle(fontSize = 30.sp)
                        )

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    val context3 = LocalContext.current
                    Button( // 3
                        onClick = {
                            context1.startActivity(
                                Intent(
                                    context3,
                                    AlumnosPaginaInicio::class.java
                                )
                            )
                        },
                        modifier = Modifier
                            .width(300.dp) // Specify the width you desire
                            .height(60.dp), shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(Orange)

                    ) {
                        Text(
                            "Nivel 3",
                            style = TextStyle(fontSize = 30.sp)
                        )

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp) // Para añadir espacio entre los botones
                    ) {
                        val context4 = LocalContext.current
                        Button( // Botón existente
                            onClick = {
                                context1.startActivity(
                                    Intent(
                                        context4,
                                        AlumnosPaginaInicio::class.java
                                    )
                                )
                            },
                            modifier = Modifier
                                .width(230.dp) // Ancho fijo para el botón Comunicador
                                .height(60.dp), shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Orange)
                        ) {
                            Text(
                                "Comunicador",
                                style = TextStyle(fontSize = 30.sp)
                            )
                        }

                        // Asumiendo que quieres mantener el mismo espacio de 16.dp entre los botones
                        Spacer(modifier = Modifier.width(16.dp))

                        Button( // Nuevo botón
                            onClick = {
                                // Acción para el nuevo botón
                            },
                            modifier = Modifier
                                .width(64.dp) // Ancho fijo para el nuevo botón
                                .height(60.dp), shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Orange)
                        ) {
                            Text(
                                "Nuevo Botón",
                                style = TextStyle(fontSize = 30.sp)
                            )
                        }
                    }
                }
            }
        }
    }
}