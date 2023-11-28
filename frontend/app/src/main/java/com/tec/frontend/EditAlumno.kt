package com.tec.frontend

import android.content.ContentValues
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tec.frontend.Api.Alumno
import com.tec.frontend.Api.Padre
import com.tec.frontend.Api.RetrofitInstance
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

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
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Edit(EstudianteId, Nivel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// @Preview(name = "Landscape Mode", showBackground = true, device = Devices.PIXEL_C, widthDp = 1280)
fun Edit(EstudianteId: Int, Nivel: Int) {
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

    var padres by remember { mutableStateOf<List<Padre>>(emptyList()) }
    var listOfPadres = arrayOf("")
    val papas = remember { mutableStateOf(listOfPadres[0])}
    val expanded = remember { mutableStateOf(false)}

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                // Make Retrofit API call on the background thread
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.apiService.getEstudiante(alumnoId = EstudianteId)
                }

                // Actualizar el estado del estudiante con la respuesta de la API
                Estudiante = response[0]

                val response2 = withContext(Dispatchers.IO) {
                    RetrofitInstance.apiService.getPadres()
                }
                padres = response2

                padres.forEach { padre ->
                    if (padre.id == Estudiante.parentId) {
                        text4 = padre.id.toString()
                        papas.value = padre.user
                    }
                }

            } catch (e: Exception) {
                // Handle error
                // You can display an error message or perform other actions
                Log.e("InfoAlumno", "Error al obtener información del estudiante", e)
            }
        }
    }

    padres.forEach { padre ->
        listOfPadres += padre.user
    }

    text1 = Estudiante.name.toString()
    text2 = Estudiante.birthYear.toString()
    text3 = Estudiante.gender.toString()
    text4 = if (Estudiante.parentId == null) "" else Estudiante.parentId.toString()
    text5 = Estudiante.description.toString()
    text6 = Estudiante.cognitiveLevel.toString()
    Surface(
        modifier = Modifier.fillMaxSize(), color = Color(0xFF4169CF)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(900.dp)
                    .background(Color.White)
                    .padding(40.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text1, fontSize = 50.sp, fontWeight = FontWeight.Medium
                    )
                    Row(
                        modifier = Modifier.padding(top = 30.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(end = 10.dp),
                            text = "Año de nacimiento: ",
                            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 32.sp)
                        )
                        TextField(
                            shape = RoundedCornerShape(0.dp),
                            modifier = Modifier.border(2.dp, Color.Gray),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White
                            ),
                            value = text2,
                            onValueChange = {
                                text2 = it
                            },
                            textStyle = TextStyle(
                                color = Color.Black, fontSize = 32.sp
                            ),
                            placeholder = {
                                Text(
                                    "Inserte año...", color = Color.Gray, fontSize = 32.sp
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                    Row(
                        modifier = Modifier.padding(top = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(end = 10.dp),
                            text = "Género: ",
                            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 32.sp)
                        )
                        Log.d("mensaje", text3)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Row {
                                RadioButton(
                                    selected = text3 == "male", onClick = {
                                        text3 = "male"
                                    }, modifier = Modifier.padding(end = 5.dp)
                                )
                                Text("Male", style = TextStyle(fontSize = 32.sp))
                            }

                            Row {
                                RadioButton(
                                    selected = text3 == "female", onClick = {
                                        text3 = "female"
                                    }, modifier = Modifier.padding(end = 5.dp)
                                )
                                Text(
                                    "Female", style = TextStyle(
                                        fontSize = 32.sp
                                    )
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.padding(top = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(end = 10.dp),
                            text = "Tutor: ",
                            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 32.sp)
                        )
                        ExposedDropdownMenuBox(
                            modifier = Modifier
                                .width(550.dp)
                                .border(2.dp, Color.Gray),
                            expanded = expanded.value,
                            onExpandedChange = {
                                expanded.value = !expanded.value
                            }
                        ) {
                            TextField(
                                value = papas.value,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
                                },
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.White,
                                    focusedContainerColor = Color.White
                                ) ,
                                modifier = Modifier
                                    .menuAnchor()
                                    .width(750.dp)
                                    .background(Color.White),
                                textStyle = TextStyle.Default.copy(
                                    fontSize = 28.sp,
                                    color = Color.Gray
                                )
                            )
                            ExposedDropdownMenu(
                                modifier = Modifier
                                    .border(2.dp, Color.Gray)
                                    .background(Color.White),
                                expanded = expanded.value,
                                onDismissRequest = { expanded.value = false },

                                ) {
                                padres.forEach { padre ->
                                    DropdownMenuItem(
                                        modifier = Modifier
                                            .width(850.dp)
                                            .padding(10.dp),
                                        text = { Text(text = padre.user, fontSize = 35.sp, lineHeight = 35.sp, fontWeight = FontWeight.Light) },
                                        onClick = {
                                            papas.value = padre.user
                                            text4 = padre.id.toString()
                                            expanded.value = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                    FourOptionsCheckBox(Nivel) { text7 = it }
                    Row(
                        modifier = Modifier.padding(top = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(end = 10.dp),
                            text = "Descripción: ",
                            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 32.sp)
                        )
                        TextField(shape = RoundedCornerShape(0.dp),
                            modifier = Modifier.border(2.dp, Color.Gray),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White
                            ),
                            value = text5,
                            onValueChange = {
                                text5 = it
                            },
                            textStyle = TextStyle(
                                color = Color.Black, fontSize = 32.sp
                            ),
                            placeholder = {
                                Text(
                                    "Inserte descripción...", color = Color.Gray, fontSize = 32.sp
                                )
                            })
                    }
                    Row(
                        modifier = Modifier.padding(top = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(end = 10.dp),
                            text = "Nivel cognitivo: ",
                            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 32.sp)
                        )
                        TextField(shape = RoundedCornerShape(0.dp),
                            modifier = Modifier.border(2.dp, Color.Gray),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White
                            ),
                            value = text6,
                            onValueChange = {
                                text6 = it
                            },
                            textStyle = TextStyle(
                                color = Color.Black, fontSize = 32.sp
                            ),
                            placeholder = {
                                Text(
                                    "Inserte nivel cognitivo...",
                                    color = Color.Gray,
                                    fontSize = 32.sp
                                )
                            })
                    }
                    val context1 = LocalContext.current

                    Row(
                        modifier = Modifier.padding(top = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                val intent = Intent(context1, InfoAlumno::class.java)
                                intent.putExtra("alumnoId", EstudianteId)
                                context1.startActivity(intent)
                            },
                            modifier = Modifier.padding(end = 20.dp),
                            shape = RoundedCornerShape(0.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        ) {
                            Text(text = "Cancelar", style = TextStyle(fontSize = 32.sp))
                        }
                        Button(
                            onClick = {
                                Log.d(
                                    "New",
                                    "Nombre: $text1, Edad: $text2, Genero: $text3, Tutor: $text4, Descripción: $text5, Cognitivo: $text6, Nivel: $text7"
                                )
                                coroutineScope.launch {
                                    val response = withContext(Dispatchers.IO) {
                                        RetrofitInstance.apiService.actualizarAlumno(
                                            alumnoId = EstudianteId, Alumno(
                                                id = EstudianteId,
                                                name = text1,
                                                birthYear = text2.toInt(),
                                                gender = text3,
                                                parentId = if (text4 == "") null else text4.toInt(),
                                                maximumMinigameLevel = text7,
                                                description = text5,
                                                cognitiveLevel = text6,
                                                therapistId = Estudiante.therapistId
                                            )
                                        )
                                    }

                                    if (response.isSuccessful) {
                                        val intent = Intent(context1, InfoAlumno::class.java)
                                        intent.putExtra("alumnoId", EstudianteId)
                                        context1.startActivity(intent)
                                    } else {
                                        val jsonError = JSONObject(response.errorBody()!!.string())
                                        val errorMessage = jsonError.getString("error");

                                        Log.d(ContentValues.TAG, errorMessage)
                                        withContext(Dispatchers.Main) {
                                            ErrorDialog.show(context1, errorMessage)
                                        }
                                    }
                                }
                            },
                            shape = RoundedCornerShape(0.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        ) {
                            Text(text = "Guardar", style = TextStyle(fontSize = 32.sp))
                        }
                    }
                }
            }
        }

    }
}

@Composable
private fun FourOptionsCheckBox(initialSelection: Int, onSelectionChanged: (Int) -> Unit): Int {
    var selectedOption by remember { mutableStateOf<Option?>(null) }
    var num by remember { mutableStateOf(initialSelection) }

    Row(
        modifier = Modifier.padding(top = 20.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Nivel Autorizado:",
            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 32.sp),
            modifier = Modifier.padding(end = 10.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(25.dp),
        ) {
            OptionRadioButton("1", Option.OPTION1, selectedOption, onOptionSelected = {
                selectedOption = it
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
    text: String, option: Option, selectedOption: Option?, onOptionSelected: (Option) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.clickable {
            if (selectedOption != option) {
                onOptionSelected(option)
            }
        }) {
        Text(text = text, style = TextStyle(fontSize = 32.sp))
        RadioButton(selected = selectedOption == option, onClick = { onOptionSelected(option) })
    }
}

enum class Option {
    OPTION1, OPTION2, OPTION3, OPTION4
}
