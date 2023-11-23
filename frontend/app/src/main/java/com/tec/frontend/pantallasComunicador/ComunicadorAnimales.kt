package com.tec.frontend.pantallasComunicador

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.Comunicador
import com.tec.frontend.Orange
import com.tec.frontend.R
import com.tec.frontend.navigateToVerbosScreen
import com.tec.frontend.ui.theme.FrontendTheme

class ComunicadorAnimales : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF4169CF)) {
                    BackButtonComunicadorAni()
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GridAni()
                    }
                }
            }
        }
    }
}

@Composable
fun BackButtonComunicadorAni() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.Top) {
        val context = LocalContext.current
        Button(
            shape = RectangleShape,
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        Comunicador::class.java
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
fun GridAni(){
    val context = LocalContext.current
    val imageIds = listOf(
        R.drawable.rinoceronte,
        R.drawable.veterinario,
        R.drawable.mascotas,
        R.drawable.zoo,
        R.drawable.alimentar
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(imageIds.size) { index ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.White, RoundedCornerShape(10.dp))
            ) {
                Image(
                    painter = painterResource(id = imageIds[index]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            navigateToVerbosScreen(context)
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview12() {
    FrontendTheme {
        //Greeting3("Android")
    }
}