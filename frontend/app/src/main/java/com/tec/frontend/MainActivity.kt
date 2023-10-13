package com.tec.frontend

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.ui.theme.FrontendTheme

val Orange = Color(0xFFEE6B11)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    startPage()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun startPage() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF4169CF),

    ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Image(
            painter = painterResource(id = R.drawable.ama),
            contentDescription = null,
            modifier = Modifier
                .size(600.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Fit
        )

        //Spacer(modifier = Modifier.height(0.dp))


        Button(
            onClick = {
                // Handle login here
            },
            modifier = Modifier
                .width(350.dp) // Specify the width you desire
                .height(111.dp), shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(Orange)

        ) {
            Text("JUGAR!",
                style = TextStyle(fontSize = 30.sp)
            )

        }
        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current
        Button(
            onClick = {
                context.startActivity(Intent(context, Bienvenido::class.java))

            },
            modifier = Modifier
                .border(2.dp, Orange, RoundedCornerShape(10.dp))
                .width(264.dp) // Specify the width you desire
                .height(45.dp), shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),



        ) {
            Text("ENTRAR COMO ADMINISTRADOR",
                color = Orange)
        }
    }
}
}
