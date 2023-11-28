package com.tec.frontend

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.pantallasNivel2.Pantalla1N2
import com.tec.frontend.pantallasNivel2.Pantalla2N2
import com.tec.frontend.pantallasNivel2.Pantalla3N2
import com.tec.frontend.pantallasNivel2.Pantalla4N2
import com.tec.frontend.pantallasNivel2.Pantalla5N2
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

data class Bubble(val x: Float, val y: Float)

class Nivel2 : ComponentActivity() {
    private var studentId: Int = -1
    private var studentName: String = " "
    private var MaximumNivelAcesso: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                studentId = intent.getIntExtra("studentId", -1)
                studentName = intent.getStringExtra("studentName").toString()
                MaximumNivelAcesso = intent.getIntExtra("MaximumNivelAcesso", -1)

                Surface(modifier = Modifier.fillMaxSize()) {
                    BackgroundImage()
                    BackButton(studentId, studentName, MaximumNivelAcesso)
                    RandomBubbles()
                }
            }
        }
    }
}

@Composable
fun BackButton(studentId: Int, studentName : String, MaximumNivelAcesso: Int) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.Top) {
        val context = LocalContext.current
        Button( // Regresar a pantalla SeleccionNivel
            shape = RectangleShape,
            onClick = {
                if (MaximumNivelAcesso >= 2) {
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
fun BackgroundImage() {
    Image(
        painter = painterResource(id = R.drawable.mar2),
        contentDescription = null, // Decorative image so no description needed
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop // or ContentScale.FillBounds to fill the bounds
    )
}

@Composable
fun RandomBubbles(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val bubbles = remember { mutableStateListOf<Bubble>() }
    val bubblePainter: Painter = painterResource(id = R.drawable.burbuja)
    val bubbleLimit = 10

    val pantallas = listOf(
        Pantalla1N2::class.java,
        Pantalla2N2::class.java,
        Pantalla3N2::class.java,
        Pantalla4N2::class.java,
        Pantalla5N2::class.java
    )

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val maxWidth = constraints.maxWidth.toFloat()
        val maxHeight = constraints.maxHeight.toFloat()

        LaunchedEffect(bubbleLimit) {
            while (bubbles.size < bubbleLimit) {
                val bubbleSize = 100f // Asumiendo que es el tamaño de la burbuja
                val randomX = Random.nextFloat() * (maxWidth - bubbleSize)
                val randomY = Random.nextFloat() * (maxHeight - bubbleSize)
                bubbles.add(Bubble(x = randomX, y = randomY))
                delay(500)
            }
        }

        bubbles.forEach { bubble ->
            Image(
                painter = bubblePainter,
                contentDescription = null,
                modifier = Modifier
                    .randomBubbleModifier(bubble)
                    .clickable {
                        // Selecciona aleatoriamente de la lista "pantallas"
                        val randomIndex = Random.nextInt(pantallas.size)
                        val nextPantalla = pantallas[randomIndex]
                        val intent = Intent(context, nextPantalla)
                        context.startActivity(intent)
                    }
            )
        }
    }
}


fun Modifier.randomBubbleModifier(bubble: Bubble): Modifier = this.then(
    Modifier.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        // Colocar la burbuja en las posiciones X e Y aleatorias dentro de los límites de la pantalla
        layout(placeable.width, placeable.height) {
            val xPosition = bubble.x.toInt().coerceIn(0, constraints.maxWidth - placeable.width)
            val yPosition = bubble.y.toInt().coerceIn(0, constraints.maxHeight - placeable.height)
            placeable.placeRelative(xPosition, yPosition)
        }
    }
)


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FrontendTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            BackgroundImage()
            RandomBubbles()
        }
    }
}
