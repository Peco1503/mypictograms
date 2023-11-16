package com.tec.frontend

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.ui.theme.FrontendTheme


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
                    .height(750.dp)
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
                    Row(modifier = Modifier.padding(top=16.dp)) {
                        Text(text = "Edad:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        Text(text = "Texto1", style = TextStyle(fontSize = 35.sp))
                    }
                    Row(modifier = Modifier.padding(top=16.dp)) {
                        Text(text = "Genero:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        Text(text = "Texto2", style = TextStyle( fontSize = 35.sp))
                    }
                    Row(modifier = Modifier.padding(top=16.dp)) {
                        Text(text = "Tutor:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        Text(text = "Texto3", style = TextStyle( fontSize = 35.sp))
                    }
                    FourOptionsCheckBox()
                    Row(modifier = Modifier.padding(top=16.dp)){
                        Text(text = "Descripción:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        Text(text = "Texto4", style = TextStyle(fontSize = 35.sp))
                    }
                    Row(modifier = Modifier.padding(top=16.dp)){
                        Text(text = "Nivel Cognitivo:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        Text(text = "Texto5", style = TextStyle(fontSize = 35.sp))
                    }
                    val context1 = LocalContext.current
                    val context2 = LocalContext.current
                    Row(modifier = Modifier.padding(top=16.dp), horizontalArrangement = Arrangement.SpaceBetween){
                        Button(onClick = { context1.startActivity(Intent(context1, DashboardProfe::class.java)) },
                                modifier = Modifier
                                .padding(15.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        ) {
                            Text(text = "Atrás", style = TextStyle(fontSize = 35.sp))
                        }
                        Button(onClick = { context2.startActivity(Intent(context2, EditAlumno::class.java)) },
                            modifier = Modifier
                                .padding(15.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                            ) {
                            Text(text = "Editar", style = TextStyle(fontSize = 35.sp))
                        }
                    }
                }
            }
        }

    }
}
@Composable
fun FourOptionsCheckBox() {
    var selectedOption by remember { mutableStateOf<Option?>(null) }

    Column {
        Text(text = "Nivel Autorizado: ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp), modifier = Modifier.padding(25.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OptionRadioButton("1", Option.OPTION1, selectedOption, onOptionSelected = { selectedOption = it })
            OptionRadioButton("2", Option.OPTION2, selectedOption, onOptionSelected = { selectedOption = it })
            OptionRadioButton("3", Option.OPTION3, selectedOption, onOptionSelected = { selectedOption = it })
            OptionRadioButton("4", Option.OPTION4, selectedOption, onOptionSelected = { selectedOption = it })
        }

        // Actions based on the selected option (you can add logic here)
        when (selectedOption) {
            Option.OPTION1 -> {
                // Option 1 selected
            }
            Option.OPTION2 -> {
                // Option 2 selected
                // Option 2 selected
            }
            Option.OPTION3 -> {
                // Option 3 selected
            }
            Option.OPTION4 -> {
                // Option 4 selected
            }
            null -> {
                // No option selected
            }
        }
    }
}

@Composable
private fun OptionRadioButton(
    text: String,
    option: Option,
    selectedOption: Option?,
    onOptionSelected: (Option) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(25.dp)
            .clickable {
                if (selectedOption != option) {
                    onOptionSelected(option)
                }
            }
    ) {
        Text(text = text, style = TextStyle(fontSize = 35.sp))
        RadioButton(
            selected = selectedOption == option,
            onClick = { onOptionSelected(option) }
        )
    }
}



