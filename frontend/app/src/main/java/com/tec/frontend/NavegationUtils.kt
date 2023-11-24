package com.tec.frontend

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tec.frontend.pantallasComunicador.Verbos
import com.tec.frontend.ui.theme.FrontendTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tec.frontend.R

fun navigateToVerbosScreen(context: Context) {
    val intent = Intent(context, Verbos::class.java)
    context.startActivity(intent)
}

@Composable
fun BarraComunicador() {
    val accentColor = Color(0xFFFF9800)
    val backgroundColor = Color(0xFFE0E0E0)

    Row(
        modifier = Modifier
            .fillMaxWidth(0.96f)
            .height(80.dp)
            .background(backgroundColor) // Fondo de la barra
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(
            onClick = { /* TODO: Acción del icono de volumen */ },
            modifier = Modifier
                .size(65.dp)
                .background(color = Color.Transparent)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_volume_up_24),
                contentDescription = "Icono de Volumen",
                tint = accentColor,
                modifier = Modifier
                    .size(40.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /* TODO: Acción del botón RESET */ },
            colors = ButtonDefaults.buttonColors(accentColor),
            modifier = Modifier
                .height(50.dp)
                .clip(RoundedCornerShape(25.dp))
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Reset",
                tint = Color.White
            )
            Text("RESET", color = Color.White, modifier = Modifier.padding(start = 8.dp))
        }
    }
}

class NavegationUtils : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview18() {
    FrontendTheme {
        //Greeting("Android")
    }
}