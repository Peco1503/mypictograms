package com.tec.frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

data class Bubble(val x: Float, val y: Float)

class Nivel2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the full size of the screen
                Surface(modifier = Modifier.fillMaxSize()) {
                    BackgroundImage()
                    RandomBubbles()
                }
            }
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
    val bubbles = remember { mutableStateListOf<Bubble>() }
    val bubblePainter: Painter = painterResource(id = R.drawable.burbuja)
    val bubbleLimit = 10

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val maxWidth = constraints.maxWidth.toFloat()
        val maxHeight = constraints.maxHeight.toFloat()

        // Iniciar la generación de burbujas
        LaunchedEffect(bubbleLimit) {
            while (bubbles.size < bubbleLimit) {
                // Asegúrate de obtener el tamaño de la imagen de la burbuja aquí, por ejemplo:
                // val bubbleSize = 100f // Reemplaza esto con el tamaño real de tu imagen en píxeles
                // Para este ejemplo, vamos a usar un tamaño ficticio de 100px para la burbuja.
                val bubbleSize = 100f
                val randomX = Random.nextFloat() * (maxWidth - bubbleSize)
                val randomY = Random.nextFloat() * (maxHeight - bubbleSize)
                bubbles.add(Bubble(x = randomX, y = randomY))
                // Añadir un delay entre la generación de cada burbuja
                delay(500)
            }
        }

        // Dibujar las burbujas en la pantalla
        bubbles.forEach { bubble ->
            Image(
                painter = bubblePainter,
                contentDescription = "Bubble",
                modifier = Modifier.randomBubbleModifier(bubble)
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
