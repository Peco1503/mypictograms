package com.tec.frontend

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.ui.theme.FrontendTheme
import java.util.*


class Nivel1 : ComponentActivity() {
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

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BackgroundImage1()
                    BackButton1(studentId, studentName, MaximumNivelAcesso)
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
fun BackgroundImage1() {
    Image(
        painter = painterResource(id = R.drawable.bignivel1),
        contentDescription = null, // Decorative image so no description needed
        modifier = Modifier.fillMaxSize(),

        contentScale = ContentScale.Crop // or ContentScale.FillBounds to fill the bounds
    )
}

@Composable
fun BackButton1(studentId: Int, studentName : String, MaximumNivelAcesso: Int) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.Top) {
        val context = LocalContext.current
        Button( // Regresar a pantalla SeleccionNivel
            shape = RectangleShape,
            onClick = {
                if (MaximumNivelAcesso >= 1) {
                    val intent = Intent(context, SeleccionNivel::class.java)
                    intent.putExtra("studentId", studentId)
                    intent.putExtra("studentName", studentName)
                    intent.putExtra("MaximumNivelAcesso", MaximumNivelAcesso)
                    context.startActivity(intent)
                } else {

                }
            },
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
fun CenteredContent(tts: TextToSpeech?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 150.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // First row with the first four images
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 0.dp, max = 200.dp), // Adjust the max height as needed
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // First image
            Image(
                painter = painterResource(id = R.drawable.abeja),
                contentDescription = "Imagen de Patito",
                modifier = Modifier
                    .weight(1f)
                    .clickable{
                        speakOut("Abeja", tts)
                    },
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_volume_up_24),
                contentDescription = "Icono de Volumen",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(60.dp)
                    .clickable {
                        speakOut("Abeja", tts)
                    },
                tint = Color.Black
            )

            // Second image
            Image(
                painter = painterResource(id = R.drawable.bufalo),
                contentDescription = "Second Image",
                modifier = Modifier
                    .weight(1f)
                    .clickable{
                        speakOut("Bufalo", tts)
                    },
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_volume_up_24),
                contentDescription = "Icono de Volumen",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(60.dp)
                    .clickable {
                        speakOut("Bufalo", tts)
                    },
                tint = Color.Black
            )

            // Third image
            Image(
                painter = painterResource(id = R.drawable.camello),
                contentDescription = "Third Image",
                modifier = Modifier
                    .weight(1f)
                    .clickable{
                        speakOut("Camello", tts)
                    },

            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_volume_up_24),
                contentDescription = "Icono de Volumen",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(60.dp)
                    .clickable {
                        speakOut("Camello", tts)
                    },
                tint = Color.Black
            )

            // Fourth image
            Image(
                painter = painterResource(id = R.drawable.caracol),
                contentDescription = "Fourth Image",
                modifier = Modifier
                    .weight(1f)
                    .clickable{
                        speakOut("Caracol", tts)
                    },
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_volume_up_24),
                contentDescription = "Icono de Volumen",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(60.dp)
                    .clickable {
                        speakOut("Caracol", tts)
                    },
                tint = Color.Black
            )
        }

        // Add a spacer to create vertical space between the first row and the second row
        Spacer(modifier = Modifier.height(100.dp))

        // Second row with the next four images
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 0.dp, max = 200.dp), // Adjust the max height as needed
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Fifth image
            Image(
                painter = painterResource(id = R.drawable.cebra),
                contentDescription = "Fifth Image",
                modifier = Modifier
                    .weight(1f)
                    .clickable{
                        speakOut("Cebra", tts)
                    },
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_volume_up_24),
                contentDescription = "Icono de Volumen",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(60.dp)
                    .clickable {
                        speakOut("Cebra", tts)
                    },
                tint = Color.Black
            )

            // Sixth image
            Image(
                painter = painterResource(id = R.drawable.delfin),
                contentDescription = "Sixth Image",
                modifier = Modifier
                    .weight(1f)
                    .clickable{
                        speakOut("delfin", tts)
                    },
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_volume_up_24),
                contentDescription = "Icono de Volumen",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(60.dp)
                    .clickable {
                        speakOut("delfin", tts)
                    },
                tint = Color.Black
            )

            // Seventh image
            Image(
                painter = painterResource(id = R.drawable.gorila),
                contentDescription = "Seventh Image",
                modifier = Modifier
                    .weight(1f)
                    .clickable{
                        speakOut("Gorila", tts)
                    },
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_volume_up_24),
                contentDescription = "Icono de Volumen",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(60.dp)
                    .clickable {
                        speakOut("Gorila", tts)
                    },
                tint = Color.Black
            )

            // Eighth image
            Image(
                painter = painterResource(id = R.drawable.perro),
                contentDescription = "Eighth Image",
                modifier = Modifier
                    .weight(1f)
                    .clickable{
                        speakOut("Perro", tts)
                    },
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_volume_up_24),
                contentDescription = "Icono de Volumen",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(60.dp)
                    .clickable {
                        speakOut("Perro", tts)
                    },
                tint = Color.Black
            )
        }
    }
}

private fun speakOut(animalName: String, tts: TextToSpeech?) {
    tts?.speak(animalName, TextToSpeech.QUEUE_FLUSH, null, "")
}