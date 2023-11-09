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
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.ui.theme.FrontendTheme

class SubirImagenes : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SubirImagenesPantalla()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Landscape Mode", showBackground = true, device = Devices.PIXEL_C, widthDp = 1280)
@Composable
fun SubirImagenesPantalla() {
    var text1 by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF4169CF)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "¡Sube tu imagen!",
                color = Color.White,
                style = TextStyle(fontSize = 50.sp),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 80.dp)
            )
            Box(
                modifier = Modifier
                    .width(821.dp)
                    .height(541.dp)
                    .background(Color.White)
                    .border(1.dp, Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    BasicTextField(
                        modifier = Modifier
                            .width(750.dp)
                            .padding(top = 20.dp)
                            .border(2.dp, Color.Gray, MaterialTheme.shapes.medium),
                        value = text1,
                        onValueChange = {
                            text1 = it
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 35.sp
                        ),
                        decorationBox = { innerTextField ->
                            if (text1.isEmpty()) {
                                Text(
                                    text = "Titulo",
                                    color = Color.Gray,
                                    style = TextStyle(
                                        fontSize = 35.sp
                                    ),
                                    modifier = Modifier
                                        .padding(16.dp)
                                )
                            }
                            innerTextField()
                        }
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    BasicTextField(
                        modifier = Modifier
                            .width(750.dp)
                            .padding(top = 1.dp)
                            .border(2.dp, Color.Gray, MaterialTheme.shapes.medium),
                        value = text1,
                        onValueChange = {
                            text1 = it
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 35.sp
                        ),
                        decorationBox = { innerTextField ->
                            if (text1.isEmpty()) {
                                Text(
                                    text = "Categoría",
                                    color = Color.Gray,
                                    style = TextStyle(
                                        fontSize = 35.sp
                                    ),
                                    modifier = Modifier
                                        .padding(16.dp)
                                )
                            }
                            innerTextField()
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    val context = LocalContext.current
                    Button(
                        modifier = Modifier
                            .padding(top = 45.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        onClick =
                        {
                            context.startActivity(Intent(context, DashboardProfe::class.java))
                        },
                    )
                    {
                        Text(
                            text = "Iniciar Sesión",
                            style = TextStyle(
                                fontSize = 35.sp
                            )
                        )
                    }
                }
            }
        }
    }
}
