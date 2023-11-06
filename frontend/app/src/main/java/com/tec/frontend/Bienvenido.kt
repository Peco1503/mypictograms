package com.tec.frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.ui.theme.FrontendTheme

class Bienvenido : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BienvenidoPage()
                }
            }
        }
    }
}

@Composable
fun BienvenidoPage() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF4169CF),

        ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,

        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(381.dp)
                    .background(Color.White)
                    .border(1.dp, Color.Black)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bienvenid@",
                        color = Color.Black,
                        style = TextStyle(fontSize = 30.sp)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = {
                            // Handle login button click here
                        },
                        modifier = Modifier
                            .background(Orange)
                            .border(2.dp, Orange, RoundedCornerShape(10.dp))
                            .width(264.dp) // Specify the width you desire
                            .height(45.dp), shape = RoundedCornerShape(10.dp)
                        ,
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                    ) {
                        Text(text = "Iniciar sesion",
                            color = Color.White,
                            style = TextStyle(fontSize = 20.sp),


                        )

                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    Button(
                        onClick = {
                            // Handle login button click here
                        },
                        modifier = Modifier
                            .border(2.dp, Orange, RoundedCornerShape(10.dp))
                            .width(264.dp) // Specify the width you desire
                            .background(Orange)
                            .height(45.dp), shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                    ) {
                        Text(
                            text = "Registrarse",
                            color = Color.White,
                            style = TextStyle(fontSize = 20.sp),
                        )
                    }
                }
            }
        }
    }
}

