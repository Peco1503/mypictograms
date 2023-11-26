package com.tec.frontend

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
                    BackButtonSN()
                }
            }
        }
    }
}

@Composable
fun BackButtonSN() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.Top) {
        val context = LocalContext.current
        Button( // Regresar a pantalla SeleccionNivel
            shape = RectangleShape,
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        AlumnosPaginaInicio::class.java
                    )
                )
            }, //shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(Orange)
        ){
            Text(
                "Atrás",
                style = TextStyle(fontSize = 35.sp)
            )
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
                        text = "Selecciona el nivel",
                        color = Color.Black,
                        style = TextStyle(fontSize = 32.sp),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    val context1 = LocalContext.current
                    Button( //1
                        shape = RectangleShape,
                        onClick = {
                            context1.startActivity(
                                Intent(
                                    context1,
                                    Nivel1::class.java
                                )
                            )
                        },
                        modifier = Modifier

                            .width(300.dp) // Specify the width you desire
                            .height(60.dp), //shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(Orange)

                    ) {
                        Text(
                            "Nivel 1",
                            style = TextStyle(fontSize = 25.sp),
                            fontWeight = FontWeight.SemiBold
                        )

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    val context2 = LocalContext.current
                    Button( //2
                        shape = RectangleShape,
                        onClick = {
                            context2.startActivity(
                                Intent(
                                    context2,
                                    Nivel2::class.java
                                )
                            )
                        },
                        modifier = Modifier
                            .width(300.dp) // Specify the width you desire
                            .height(60.dp), //shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(Orange)

                    ) {
                        Text(
                            "Nivel 2",
                            style = TextStyle(fontSize = 25.sp),
                            fontWeight = FontWeight.SemiBold
                        )

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    val context3 = LocalContext.current
                    Button( // 3
                        shape = RectangleShape,
                        onClick = {
                            context1.startActivity(
                                Intent(
                                    context3,
                                    Nivel3::class.java
                                )
                            )
                        },
                        modifier = Modifier
                            .width(300.dp) // Specify the width you desire
                            .height(60.dp), //shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(Orange)

                    ) {
                        Text(
                            "Nivel 3",
                            style = TextStyle(fontSize = 25.sp),
                            fontWeight = FontWeight.SemiBold
                        )

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp) // Para añadir espacio entre los botones
                    ) {
                        val context4 = LocalContext.current
                        Button( // Botón existente
                            shape = RectangleShape,
                            onClick = {
                                context1.startActivity(
                                    Intent(
                                        context4,
                                        Comunicador::class.java
                                    )
                                )
                            },
                            modifier = Modifier
                                .width(220.dp) // Ancho fijo para el botón Comunicador
                                .height(60.dp), //shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Orange)
                        ) {
                            Text(
                                "Comunicador",
                                style = TextStyle(fontSize = 25.sp),
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // Distancia entre boton 'Comunicador' y 'Camara'
                        Spacer(modifier = Modifier.width(2.dp))
                        val context5 = LocalContext.current
                        Button( // Nuevo botón
                            shape = RectangleShape,
                            onClick = {
                                context1.startActivity(
                                    Intent(
                                        context5,
                                        SubirImagenes::class.java
                                    )
                                )
                            },
                            modifier = Modifier
                                .width(64.dp) // Ancho fijo para el nuevo botón
                                .height(60.dp), //shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Orange)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                                contentDescription = "Icono de Camara",
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}