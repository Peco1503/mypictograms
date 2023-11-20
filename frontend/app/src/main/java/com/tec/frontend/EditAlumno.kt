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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tec.frontend.Api.Alumno
import com.tec.frontend.Api.RetrofitInstance
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditAlumno : ComponentActivity() {
    private var EstudianteId: Int = -1
    private var Nivel: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                EstudianteId = intent.getIntExtra("alumnoId", -1)
                Nivel = intent.getIntExtra("Nivel", -1)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Edit(EstudianteId, Nivel)
                }
            }
        }
    }
}

@Composable
fun Edit(EstudianteId: Int, Nivel: Int)
{
    val coroutineScope = rememberCoroutineScope()
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var text3 by remember { mutableStateOf("") }
    var text4 by remember { mutableStateOf("") }
    var text5 by remember { mutableStateOf("") }
    var text6 by remember { mutableStateOf("") }
    var text7 by remember { mutableStateOf(Nivel) }
    val viewModel: RegistroViewModel = viewModel()

    var Estudiante by remember { mutableStateOf(Alumno()) }

    LaunchedEffect(Unit){
        coroutineScope.launch {
            try {
                // Make Retrofit API call on the background thread
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.apiService.getEstudiante(alumnoId = EstudianteId)
                }

                // Actualizar el estado del estudiante con la respuesta de la API
                Estudiante = response[0]

            } catch (e: Exception) {
                // Handle error
                // You can display an error message or perform other actions
                Log.e("InfoAlumno", "Error al obtener información del estudiante", e)
            }
        }
    }
    text1 = Estudiante.name.toString()
    text2 = Estudiante.birthYear.toString()
    text3 = Estudiante.gender.toString()
    text4 = if (Estudiante.parentId == null) "" else Estudiante.parentId.toString()
    text5 = Estudiante.description.toString()
    text6 = Estudiante.cognitiveLevel.toString()
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
                        Text(text = "Tutor:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        EditText(text4) { newText -> text4 = newText }
                    }
                    FourOptionsCheckBox(Nivel) { text7 = it }
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
                        Button(onClick = {
                            val intent = Intent(context1, InfoAlumno::class.java)
                            intent.putExtra("alumnoId", EstudianteId)
                            context1.startActivity(intent)
                                         },
                            modifier = Modifier
                                .padding(15.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        ) {
                            Text(text = "Cancelar", style = TextStyle(fontSize = 35.sp))
                        }
                        Button(onClick = {
                            Log.d("New", "Nombre: $text1, Edad: $text2, Genero: $text3, Tutor: $text4, Descripción: $text5, Cognitivo: $text6, Nivel: $text7")
                            Estudiante.therapistId?.let {
                                viewModel.updateAlumno(
                                    EstudianteId,
                                    text1,
                                    text2.toInt(),
                                    text3,
                                    if (text4 == "") null else text4.toInt(),
                                    text7,
                                    text5,
                                    text6,
                                    it
                                )
                            }
                                val intent = Intent(context1, InfoAlumno::class.java)
                                intent.putExtra("alumnoId", EstudianteId)
                                context1.startActivity(intent)
                            },
                            modifier = Modifier
                                .padding(15.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        ) {
                            Text(text = "Guardar", style = TextStyle(fontSize = 35.sp))
                        }
                    }
                }
            }
        }

    }
}


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
            .height(65.dp)
            .border(2.dp, Color.Gray, MaterialTheme.shapes.medium),
        placeholder = {
            Text(
                txt,
                style = TextStyle(fontSize = 20.sp),
                color = Color.Gray
            )
        }
    )
}

@Composable
private fun FourOptionsCheckBox(initialSelection: Int, onSelectionChanged: (Int) -> Unit): Int {
    var selectedOption by remember { mutableStateOf<Option?>(null) }
    var num by remember { mutableStateOf(initialSelection) }

    Column {
        Text(
            text = "Nivel Autorizado: ",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp),
            modifier = Modifier.padding(15.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OptionRadioButton("1", Option.OPTION1, selectedOption, onOptionSelected = { selectedOption = it })
            OptionRadioButton("2", Option.OPTION2, selectedOption, onOptionSelected = { selectedOption = it })
            OptionRadioButton("3", Option.OPTION3, selectedOption, onOptionSelected = { selectedOption = it })
            OptionRadioButton("4", Option.OPTION4, selectedOption, onOptionSelected = { selectedOption = it })
        }

        when (selectedOption) {
            Option.OPTION1 -> {
                num = 1
                onSelectionChanged(num)
            })
            OptionRadioButton("2", Option.OPTION2, selectedOption, onOptionSelected = {
                selectedOption = it
                num = 2
                onSelectionChanged(num)
            })
            OptionRadioButton("3", Option.OPTION3, selectedOption, onOptionSelected = {
                selectedOption = it
                num = 3
                onSelectionChanged(num)
            })
            OptionRadioButton("4", Option.OPTION4, selectedOption, onOptionSelected = {
                selectedOption = it
                num = 4
                onSelectionChanged(num)
            })
        }

        // Set initial selection based on the provided value
        if (selectedOption == null) {
            selectedOption = when (num) {
                1 -> Option.OPTION1
                2 -> Option.OPTION2
                3 -> Option.OPTION3
                4 -> Option.OPTION4
                else -> null
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

enum class Option {
    OPTION1, OPTION2, OPTION3, OPTION4
}
