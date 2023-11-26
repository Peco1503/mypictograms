package com.tec.frontend

import android.content.Intent
import android.os.Bundle
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.ui.text.TextStyle
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.ui.theme.FrontendTheme
import coil.compose.AsyncImage
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import com.tec.frontend.util.ImageUploader

class CrearCategoria : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    CrearCategoriaPantalla()
                    BackButtonCC()
                }
            }
        }
    }
}

@Composable
fun BackButtonCC() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), verticalAlignment = Alignment.Top
    ) {
        val context = LocalContext.current
        Button( // Regresar a pantalla SeleccionNivel
            shape = RectangleShape,
            onClick = {
                context.startActivity(
                    Intent(
                        context, SubirImagenes::class.java
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(Orange)
        ) {
            Text(
                "Atrás", style = TextStyle(fontSize = 35.sp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(name = "Landscape Mode", showBackground = true, device = Devices.PIXEL_C, widthDp = 1280)
fun CrearCategoriaPantalla() {
    // Variables que identifican a la imagen
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    // Variables para la selección del URI y subir la imagen
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImageUri = uri })

    Surface(
        modifier = Modifier.fillMaxSize(), color = Color(0xFF4169CF)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "¡Crea tu nueva categoría!",
                color = Color.White,
                style = TextStyle(fontSize = 50.sp),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 60.dp)
            )
            Box(
                modifier = Modifier
                    .width(821.dp)
                    .height(541.dp)
                    .background(Color.White)
                    .border(1.dp, Color.Black), contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    TextField(
                        shape = RoundedCornerShape(0.dp),
                        modifier = Modifier
                            .width(700.dp)
                            .padding(top = 50.dp)
                            .border(2.dp, Color.Gray)
                            .background(Color.White),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        ),
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        textStyle = TextStyle(
                            color = Color.Black, fontSize = 35.sp
                        ),
                        placeholder = {
                            Text(
                                "Título", color = Color.Gray, fontSize = 35.sp
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp) // Para añadir espacio entre los botones
                    ) {
                        Button(
                            modifier = Modifier.padding(top = 30.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                            onClick = {
                                singlePhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            },
                        ) {
                            Text(
                                text = "Seleccionar Portada", style = TextStyle(
                                    fontSize = 30.sp
                                )
                            )
                        }
                        val context = LocalContext.current
                        Button(
                            modifier = Modifier.padding(top = 30.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                            onClick = {
                                selectedImageUri?.let {
                                    ImageUploader.uploadToStorage(
                                        uri = it,
                                        context = context,
                                        userFolder = "1-Felipe González",
                                        category = name,
                                        imageTitle = "portada"
                                    )
                                }
                            },
                        ) {
                            Text(
                                text = "Crear", style = TextStyle(
                                    fontSize = 30.sp
                                )
                            )
                        }
                    }
                    LazyColumn(
                    ) {
                        item {
                            AsyncImage(
                                model = selectedImageUri,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(
                                        width = 500.dp, height = 250.dp
                                    )
                                    .padding(
                                        top = 20.dp
                                    ),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }
            }
        }
    }
}
