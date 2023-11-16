package com.tec.frontend.pantallasNivel2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.Nivel2
import com.tec.frontend.Orange
import com.tec.frontend.R
import com.tec.frontend.ui.theme.FrontendTheme

class Pantalla4N2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                Surface(modifier = Modifier.fillMaxSize()){
                    BackgroundImage4()
                    BackButton4()
                    CenteredContent4()
                }
            }
        }
    }
}

@Composable
fun BackButton4() {
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
                        Nivel2::class.java
                    )
                )
            },
            border = BorderStroke(5.dp, Color.Black),
            modifier = Modifier
                .width(116.dp)
                .height(34.dp), //shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(Orange)
        ){
            Text(
                "ATRAS",
                style = TextStyle(fontSize = 12.sp)
            )
        }
    }
}

@Composable
fun BackgroundImage4() {
    Image(
        painter = painterResource(id = R.drawable.habitattigre),
        contentDescription = null, // Decorative image so no description needed
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop // or ContentScale.FillBounds to fill the bounds
    )
}

@Composable
fun CenteredContent4() {
    Column(
        modifier = Modifier
            .fillMaxSize() // Para que el Column use todo el espacio disponible
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Alinea los hijos horizontalmente en el centro
        verticalArrangement = Arrangement.Center // Alinea los hijos verticalmente en el centro
    ) {
        // Imagen de patito
        Image(
            painter = painterResource(id = R.drawable.tigre),
            contentDescription = "Imagen de Tigre",
            modifier = Modifier.size(300.dp) // Tamaño de la imagen, ajusta según necesidad
        )

        // Ícono de volumen, debajo de la imagen
        Icon(
            painter = painterResource(id = R.drawable.baseline_volume_up_24),
            contentDescription = "Icono de Volumen",
            modifier = Modifier
                .padding(top = 16.dp)
                .size(60.dp),
            tint = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    FrontendTheme {
        Surface(modifier = Modifier.fillMaxSize()){
            BackgroundImage()
        }
    }
}