package com.tec.frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.ui.theme.FrontendTheme
import androidx.compose.foundation.layout.Column as Column
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf


class InfoAlumno : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    infoAlumno()
                }
            }
        }
    }
}

@Composable
fun infoAlumno()
{
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF4169CF)
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(650.dp)
                    .height(700.dp)
                    .background(Color.White),
                contentAlignment = Alignment.TopCenter
            )
            {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text(
                        text = "Alumno",
                        style = TextStyle(
                            fontSize = 55.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    Row {
                        Text(text = "Edad:  ", style = TextStyle())
                        Text(text = "Texto1")
                    }
                    Row {
                        Text(text = "Genero:  ")
                        Text(text = "Texto2")
                    }
                    Row {
                        Text(text = "Tutor:  ")
                        Text(text = "Texto3")
                    }
                    FourOptionsCheckBox()

                }
            }
        }

    }
}
@Composable
fun FourOptionsCheckBox() {
    var option1CheckedState by remember { mutableStateOf(false) }
    var option2CheckedState by remember { mutableStateOf(false) }
    var option3CheckedState by remember { mutableStateOf(false) }
    var option4CheckedState by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Nivel Autorizado: ", style = TextStyle(fontSize = 35.sp), modifier = Modifier.padding(25.dp))
            Text(text = "1", style = TextStyle(fontSize = 35.sp), modifier = Modifier.padding(top = 25.dp))
            Checkbox(
                checked = option1CheckedState,
                onCheckedChange = { option1CheckedState = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(25.dp)
            )
            Text(text = "2", style = TextStyle(fontSize = 35.sp), modifier = Modifier.padding(top = 25.dp))
            Checkbox(
                checked = option2CheckedState,
                onCheckedChange = { option2CheckedState = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(25.dp)
            )
            Text(text = "3", style = TextStyle(fontSize = 35.sp), modifier = Modifier.padding(top = 25.dp))
            Checkbox(
                checked = option3CheckedState,
                onCheckedChange = { option3CheckedState = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(25.dp)
            )
            Text(text = "4", style = TextStyle(fontSize = 35.sp), modifier = Modifier.padding(top = 25.dp))
            Checkbox(
                checked = option4CheckedState,
                onCheckedChange = { option4CheckedState = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(25.dp)
            )
        }

        // Acciones basadas en la selección de opciones (puedes agregar lógica aquí)
        if (option1CheckedState) {
            // Opción 1 seleccionada
        }

        if (option2CheckedState) {
            // Opción 2 seleccionada
        }
        if (option3CheckedState) {
            // Opción 3 seleccionada
        }

        if (option4CheckedState) {
            // Opción 4 seleccionada
        }
    }
}

