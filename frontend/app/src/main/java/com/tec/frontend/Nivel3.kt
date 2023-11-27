package com.tec.frontend

import androidx.compose.ui.unit.dp
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.zIndex
import kotlin.math.roundToInt
import kotlin.math.sqrt


class Nivel3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    BackgroundImageN3()
                    BackButtonN3()

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 32.dp)
                            .padding(top = 60.dp), // Space between the columns of images with respect to the top of the screen
                        horizontalArrangement = Arrangement.Center, // Space the columns evenly
                        verticalAlignment = Alignment.Top
                    ) {
                        // Box to wrap the draggable images with a higher zIndex
                        Box(
                            modifier = Modifier.zIndex(1f)
                        ) {
                            // Column 1 - Draggable images
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                MultipleDraggableObject(imageRes = R.drawable.tortucolor)
                                MultipleDraggableObject(imageRes = R.drawable.serpcolor)
                                MultipleDraggableObject(imageRes = R.drawable.pelicolor)
                                MultipleDraggableObject(imageRes = R.drawable.lobocolor)
                                MultipleDraggableObject(imageRes = R.drawable.cococolor)
                                MultipleDraggableObject(imageRes = R.drawable.koalacolor)
                            }
                        }

                        Spacer(modifier = Modifier.width(500.dp))

                        // Column 2 - Non-draggable images
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Images in the second column without drag functionality
                            NonDraggableObject(imageRes = R.drawable.tortugris)
                            NonDraggableObject(imageRes = R.drawable.serpgris)
                            NonDraggableObject(imageRes = R.drawable.peligris)
                            NonDraggableObject(imageRes = R.drawable.lobogris)
                            NonDraggableObject(imageRes = R.drawable.cocogris)
                            NonDraggableObject(imageRes = R.drawable.koalagris)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MultipleDraggableObject(
    imageRes: Int,
    modifier: Modifier = Modifier,
    onCollision: () -> Unit = {}
) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var isFrozen by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .offset {
                IntOffset(
                    offsetX.roundToInt(),
                    offsetY.roundToInt()
                )
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    if (!isFrozen) {
                        change.consumeAllChanges()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y

                        // Check for collision with non-draggable objects
                        val collisionDetected = checkCollision(offsetX, offsetY)
                        if (collisionDetected) {
                            isFrozen = true
                            onCollision()
                        }
                    }
                }
            }
            .size(120.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Draggable Image"
        )
    }
}

@Composable
fun NonDraggableObject(imageRes: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(120.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Non-Draggable Image"
        )
    }
}


// Function to check for collision with non-draggable objects
fun checkCollision(offsetX: Float, offsetY: Float): Boolean {
    // Adjust these values based on your layout and collision criteria
    val collisionThreshold = 60.dp
    val nonDraggableObjects = listOf(
        Offset(1100f, 0f),
        // Add more non-draggable object positions as needed
    )

    for (nonDraggableObject in nonDraggableObjects) {
        if (offsetX < nonDraggableObject.x + collisionThreshold.value &&
            offsetX + collisionThreshold.value > nonDraggableObject.x &&
            offsetY < nonDraggableObject.y + collisionThreshold.value &&
            offsetY + collisionThreshold.value > nonDraggableObject.y
        ) {
            return true
        }
    }

    return false
}


@Composable
fun BackButtonN3() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        val context = LocalContext.current
        Button(
            // Regresar a pantalla SeleccionNivel
            shape = RectangleShape,
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        SeleccionNivel::class.java
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                "Atrás",
                style = TextStyle(fontSize = 35.sp)
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    FrontendTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            BackgroundImageN3()
        }
    }
}