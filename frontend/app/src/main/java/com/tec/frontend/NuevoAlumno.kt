package com.tec.frontend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.lifecycle.viewmodel.compose.viewModel


class NuevoAlumno : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    New()
                }
            }
        }
    }
}
@Composable
fun New()
{
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var text3 by remember { mutableStateOf("") }
    var text4 by remember { mutableStateOf("") }
    var text5 by remember { mutableStateOf("") }
    var text6 by remember { mutableStateOf("") }
    var text7 by remember { mutableStateOf(0) }
    val viewModel: RegistroViewModel = viewModel()
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
                    .width(750.dp)
                    .height(775.dp)
                    .background(Color.White),
                contentAlignment = Alignment.TopCenter
            )
            {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Row(modifier = Modifier.padding(top=16.dp)) {
                        Text(text = "Nombre:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        EditText(text1) { newText -> text1 = newText }
                    }
                    Row(modifier = Modifier.padding(top=16.dp)) {
                        Text(text = "Año de nacimiento:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        EditText(text2) { newText -> text2 = newText }
                    }
                    Row(modifier = Modifier.padding(top=16.dp)) {
                        Text(text = "Genero:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        EditText(text3) { newText -> text3 = newText }
                    }
                    Row(modifier = Modifier.padding(top=16.dp)) {
                        Text(text = "IdTutor:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        EditText(text4) { newText -> text4 = newText }
                    }
                    text7 = fourOptionsCheckBox()
                    Row(modifier = Modifier.padding(top=16.dp)){
                        Text(text = "Descripción:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        EditText(text5) { newText -> text5 = newText }
                    }
                    Row(modifier = Modifier.padding(top=16.dp)){
                        Text(text = "Nivel Cognitivo:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        EditText(text6) { newText -> text6 = newText }
                    }
                    val context1 = LocalContext.current
                    Row(modifier = Modifier.padding(top=16.dp), horizontalArrangement = Arrangement.SpaceBetween){
                        Button(onClick = { context1.startActivity(Intent(context1, DashboardProfe::class.java)) },
                            modifier = Modifier
                                .padding(15.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        ) {
                            Text(text = "Atrás", style = TextStyle(fontSize = 35.sp))
                        }
                        Button(onClick = {
                            Log.d("New", "Nombre: $text1, Edad: $text2, Genero: $text3, Tutor: $text4, Descripción: $text5, Cognitivo: $text6, Nivel: $text7")
                            viewModel.registerAlumno(text1, text2.toInt(), text3, text4.toInt(), text7, text5, text6)
                        },
                            modifier = Modifier
                                .padding(15.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        ) {
                            Text(text = "Agregar Alumno", style = TextStyle(fontSize = 35.sp))
                        }
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditText(txt: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = txt,
        onValueChange = {
            onTextChange(it)
        },
        textStyle = TextStyle(color = Color.Black, fontSize = 28.sp),
        modifier = Modifier
            .width(350.dp)
            .height(60.dp)
            .border(2.dp, Color.Gray, MaterialTheme.shapes.medium),
        placeholder = {
            Text("Ingrese Informacion",
                style = TextStyle(fontSize = 23.sp),
                color = Color.Gray
            )
        }
    )
}

@Composable
private fun fourOptionsCheckBox(): Int {
    var selectedOption by remember { mutableStateOf<Option?>(null) }
    var num : Int = 0

    Column {
        Text(text = "Nivel Autorizado: ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp), modifier = Modifier.padding(15.dp))

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
                num = 1
            }
            Option.OPTION2 -> {
                num = 2
            }
            Option.OPTION3 -> {
                num = 3
            }
            Option.OPTION4 -> {
                num = 4
            }
            null -> {
                // No option selected
            }
        }
    }
    return num
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
