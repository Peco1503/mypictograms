package com.tec.frontend

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.ui.theme.FrontendTheme

class PerfilAlumnoPadre : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    PerfilAlumnoPadres(activityContext = this)
                }
            }
        }
    }
}

@Composable
// @Preview(name = "Landscape Mode", showBackground = true, device = Devices.PIXEL_C, widthDp = 1280)
fun PerfilAlumnoPadres(activityContext: ComponentActivity) {
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
                    .width(500.dp)
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
                        text = "Alumno 1",
                        style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold),
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Row(modifier = Modifier.padding(top = 30.dp)) {
                        Text(
                            text = "Edad:",
                            fontWeight = FontWeight.Medium,
                            fontSize = 35.sp,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(text = "Texto1", fontSize = 35.sp)
                    }
                    Row(modifier = Modifier.padding(top = 20.dp)) {
                        Text(
                            text = "Género:",
                            fontWeight = FontWeight.Medium,
                            fontSize = 35.sp,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(text = "Texto2", fontSize = 35.sp)
                    }
                    Row(modifier = Modifier.padding(top = 20.dp)) {
                        Text(
                            text = "Tutora:",
                            fontWeight = FontWeight.Medium,
                            fontSize = 35.sp,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(text = "Texto3", fontSize = 35.sp)
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
