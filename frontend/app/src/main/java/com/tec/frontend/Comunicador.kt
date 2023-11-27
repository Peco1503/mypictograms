package com.tec.frontend

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.Api.Alumno
import com.tec.frontend.Api.Category
import com.tec.frontend.Api.RetrofitInstance
import com.tec.frontend.Api.RetrofitInstance.apiService
import com.tec.frontend.pantallasComunicador.ComunicadorAlimentos
import com.tec.frontend.pantallasComunicador.ComunicadorAnimales
import com.tec.frontend.pantallasComunicador.ComunicadorCalendario
import com.tec.frontend.pantallasComunicador.ComunicadorClima
import com.tec.frontend.pantallasComunicador.ComunicadorDeportes
import com.tec.frontend.pantallasComunicador.ComunicadorEscuela
import com.tec.frontend.pantallasComunicador.ComunicadorOficina
import com.tec.frontend.pantallasComunicador.ComunicadorSalud
import com.tec.frontend.pantallasComunicador.ComunicadorTransporte
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Comunicador : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF4169CF)) {
                    //BackButtonComunicador()
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        BackButtonComunicador(activityContext=this@Comunicador)
                        ImageGrid()
                    }
                }
            }
        }
    }
}

@Composable
fun BackButtonComunicador(activityContext: ComponentActivity) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.Top) {
        Button(
            shape = RectangleShape,
            onClick = {
                activityContext.finish()
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
fun ImageGrid() {
    var categories by remember { mutableStateOf<List<Category>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                // Make Retrofit API call on the background thread
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.apiService.getCategory(1)
                }
                categories = response

            } catch (e: Exception) {
                Log.d(ContentValues.TAG, e.toString())
            }
        }
    }

    var imageLabels = listOf<String>()

    // Variables para Category
    //var listOfCategoryNames = arrayOf("")
    categories.forEach { categorie->
        imageLabels = imageLabels + categorie.name.toString()
    }

    var listOfCategoryThumbnail = arrayOf("")
    categories.forEach{ categorie ->
        listOfCategoryThumbnail += categorie.thumbnail ?: "No se encontraron thumbnails"
    }


    val imageIds = listOf(
        R.drawable.escuela,
        R.drawable.deportes,
        R.drawable.alimentos,
        R.drawable.salud,
        R.drawable.animales,
        R.drawable.transporte,
        R.drawable.clima,
        R.drawable.calendario,
        R.drawable.oficina
    )
    val destinations = listOf(
        ComunicadorEscuela::class.java,
        ComunicadorDeportes::class.java,
        ComunicadorAlimentos::class.java,
        ComunicadorSalud::class.java,
        ComunicadorAnimales::class.java,
        ComunicadorTransporte::class.java,
        ComunicadorClima::class.java,
        ComunicadorCalendario::class.java,
        ComunicadorOficina::class.java
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(imageLabels.size) { index ->
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = imageIds[index]),
                    contentDescription = imageLabels[index],
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            val intent = Intent(context, destinations[index])
                            context.startActivity(intent)
                        }
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                ) {
                    Text(
                        text = imageLabels[index],
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            letterSpacing = 1.5.sp
                        ),
                        color = Color.White
                    )
                }
            }
        }
    }
}