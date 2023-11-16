package com.tec.frontend

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.ui.theme.FrontendTheme
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlin.math.roundToInt
import kotlin.math.sqrt

class Nivel3 : ComponentActivity() {

    private val animals = listOf(
        Animal("coco", R.drawable.cococolor, R.drawable.cocogris),
        Animal("koala", R.drawable.koalacolor, R.drawable.koalagris),
        Animal("lobo", R.drawable.lobocolor, R.drawable.lobogris),
        Animal("pelicano", R.drawable.pelicolor, R.drawable.peligris),
        Animal("serpiente", R.drawable.serpcolor, R.drawable.serpgris),
        Animal("tortuga", R.drawable.tortucolor, R.drawable.tortugris)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the full size of the screen
                Surface(modifier = Modifier.fillMaxSize()) {
                    BackgroundImageN3()
                    BackButtonN3()
                    ShadowMatchingGame(animals = animals)
                }
            }
        }
    }
}

@Composable
fun BackButtonN3() {
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
                        SeleccionNivel::class.java
                    )
                )
            },
            modifier = Modifier
                .width(116.dp)
                .height(34.dp),
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
fun BackgroundImageN3() {
    Image(
        painter = painterResource(id = R.drawable.nivel3bg2),
        contentDescription = null, // Decorative image so no description needed
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop // or ContentScale.FillBounds to fill the bounds
    )
}

data class Animal(
    val name: String,
    val imageRes: Int,
    val shadowRes: Int,
    var isMatched: Boolean = false,
    var position: Offset = Offset(0f, 0f) // Posición inicial del animal
)


@Composable
fun DraggableAnimal(
    animal: Animal,
    currentPosition: Offset,
    onPositionChange: (Offset) -> Unit
) {
    // This state will be used to handle the offset from the drag gesture
    var position by remember { mutableStateOf(currentPosition) }
    // This will tell us whether the animal is being dragged
    var isDragging by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .offset { IntOffset(position.x.roundToInt(), position.y.roundToInt()) }
            .draggable(
                state = rememberDraggableState { delta ->
                    // Actualiza solo la coordenada x para el movimiento horizontal
                    position = position.copy(x = position.x + delta)
                    onPositionChange(position)
                },
                onDragStarted = { isDragging = true },
                onDragStopped = { isDragging = false },
                orientation = Orientation.Horizontal,
            )
    ) {
        Image(
            painter = painterResource(id = if (isDragging) animal.imageRes else animal.shadowRes),
            contentDescription = "Draggable image of ${animal.name}"
        )
    }
}

@Composable
fun ShadowTarget(animal: Animal, onSelectShadow: (Animal) -> Unit) {
    Box(modifier = Modifier
        .size(100.dp)
        .padding(8.dp)
        .clickable { onSelectShadow(animal) }) {
        if (!animal.isMatched) {
            Image(
                painter = painterResource(id = animal.shadowRes),
                contentDescription = "Shadow of ${animal.name}",
                modifier = Modifier.matchParentSize()
            )
        }
    }
}


@Composable
fun ShadowMatchingGame(animals: List<Animal>) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Columna para los animales de color en el lado izquierdo con padding agregado
        Column(modifier = Modifier
            .weight(1f)
            .padding(start = 20.dp) // Ajusta este valor según sea necesario para mover las imágenes hacia la derecha
        ) {
            animals.forEach { animal ->
                Image(
                    painter = painterResource(id = animal.imageRes),
                    contentDescription = "Image of ${animal.name}",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp)
                )
            }
        }

        // Columna para las sombras de los animales en el lado derecho
        Column(
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.End)
        ) {
            for (i in animals.indices step 2) {
                Row {
                    if (i < animals.size) {
                        ShadowTarget(animal = animals[i], onSelectShadow = {})
                    }
                    if (i + 1 < animals.size) {
                        ShadowTarget(animal = animals[i + 1], onSelectShadow = {})
                    }
                }
            }
        }
    }
}



fun isNearShadow(animal: Animal, shadow: Animal?, threshold: Float = 100f): Boolean {
    // Comprueba si la sombra es nula
    if (shadow == null) return false

    // Calcula la distancia entre el centro del animal y el centro de la sombra
    val dx = animal.position.x - shadow.position.x
    val dy = animal.position.y - shadow.position.y
    val distance = sqrt(dx * dx + dy * dy)

    // Devuelve true si la distancia es menor que el umbral
    return distance <= threshold
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    FrontendTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            BackgroundImageN3()
        }
    }
}