package com.tec.frontend.pantallasNivel2

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import com.tec.frontend.SeleccionNivel
import com.tec.frontend.ui.theme.FrontendTheme

class Pantalla1N2 : ComponentActivity() {
    private var studentId: Int = -1
    private var studentName: String = " "
    private var MaximumNivelAcesso: Int = 1

    private var tts: TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(this) { status ->
            if (status != TextToSpeech.ERROR) {
                // Set the language here if needed
            }
        }
        setContent {
            FrontendTheme {
                studentId = intent.getIntExtra("studentId", -1)
                studentName = intent.getStringExtra("studentName").toString()
                MaximumNivelAcesso = intent.getIntExtra("MaximumNivelAcesso", -1)
                Surface(modifier = Modifier.fillMaxSize()){
                    BackgroundImage(tts)
                    BackButtonBubble(studentId, studentName, MaximumNivelAcesso)
                    CenteredContent(tts)
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        tts?.shutdown()
    }
}

@Composable
fun BackButtonBubble(studentId: Int, studentName : String, MaximumNivelAcesso: Int) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.Top) {
        val context = LocalContext.current
        Button( // Regresar a pantalla SeleccionNivel
            shape = RectangleShape,
            onClick = {
                val intent = Intent(context, Nivel2::class.java)
                intent.putExtra("studentId", studentId)
                intent.putExtra("studentName", studentName)
                intent.putExtra("MaximumNivelAcesso", MaximumNivelAcesso)
                context.startActivity(intent)
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
fun BackgroundImage(tts: TextToSpeech?) {
    val context = LocalContext.current

    Image(
        painter = painterResource(id = R.drawable.pato),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                speakOut("Pato", tts)
            },
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CenteredContent(tts: TextToSpeech?) {
    Column(
        modifier = Modifier
            .fillMaxSize() // Para que el Column use todo el espacio disponible
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Alinea los hijos horizontalmente en el centro
        verticalArrangement = Arrangement.Center // Alinea los hijos verticalmente en el centro
    ) {
        // Imagen de patito
        Image(
            painter = painterResource(id = R.drawable.patitoimg),
            contentDescription = "Imagen de Patito",
            modifier = Modifier.size(300.dp) // Tamaño de la imagen, ajusta según necesidad
        )

        // Ícono de volumen, debajo de la imagen
        Icon(
            painter = painterResource(id = R.drawable.baseline_volume_up_24),
            contentDescription = "Icono de Volumen",
            modifier = Modifier
                .padding(top = 16.dp)
                .size(60.dp)
                .clickable {
                    speakOut("Pato", tts)
                },
            tint = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FrontendTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val context = LocalContext.current
            val tts = TextToSpeech(context) { status ->
                if (status != TextToSpeech.ERROR) {
                    // Set the language here if needed
                }
            }
            BackgroundImage(tts)
        }
    }
}
private fun speakOut(animalName: String, tts: TextToSpeech?) {
    tts?.speak(animalName, TextToSpeech.QUEUE_FLUSH, null, "")
}