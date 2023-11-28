package com.tec.frontend

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.ui.text.TextStyle
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.Api.Alumno
import com.tec.frontend.Api.RetrofitInstance
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlumnosPaginaInicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaInicioAlum()
                    BackButtonPI()
                }
            }
        }
    }
}

@Composable
fun BackButtonPI() {
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
                        MainActivity::class.java
                    )
                )
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Landscape Mode", showBackground = true, device = Devices.PIXEL_C, widthDp = 1040)
@Composable
fun PantallaInicioAlum() {
    // Fetch para obtener el nombre de los alumnos
    val alumnoDefault = Alumno(id = 0,
        name = "",
        birthYear = null,
        gender = null,
        parentId = null,
        maximumMinigameLevel= null,
        description = null,
        cognitiveLevel = null,
        therapistId = null)
    var alumnos by remember { mutableStateOf(listOf(alumnoDefault)) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit){
        coroutineScope.launch {
            try {
                // Make Retrofit API call on the background thread
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.apiService.getEstudiantes()
                }

                // Assuming response contains an "id" and "type" field
                alumnos = response

            } catch (e: Exception) {
                // Handle error
                // You can display an error message or perform other actions
                Log.d(ContentValues.TAG, e.toString())
            }
        }
    }

    // Variables para el dropdown menu de alunmno
    var listOfStudents = arrayOf("")
    val student = remember { mutableStateOf(listOfStudents[0])}
    val selectedStudent = remember {  mutableStateOf(alumnos[0]) }
    val expanded = remember { mutableStateOf(false)}

    alumnos.forEach { alumno ->
        listOfStudents += alumno.name ?: "No se encontro nombre de alumn@"
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF4169CF)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "¿LIST@ PARA JUGAR?",
                color = Color.White,
                style = TextStyle(fontSize = 30.sp),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 180.dp)
            )
            Box(
                modifier = Modifier
                    .width(821.dp)
                    .height(341.dp)
                    .background(Color.White)
                    .border(1.dp, Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Selecciona tu nombre",
                        color = Color.Black,
                        style = TextStyle(fontSize = 45.sp),
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    ExposedDropdownMenuBox(
                        modifier = Modifier
                            .width(650.dp)
                            .border(2.dp, Color.Gray, MaterialTheme.shapes.medium),
                        expanded = expanded.value,
                        onExpandedChange = {
                            expanded.value = !expanded.value
                        }
                    ) {
                        TextField(
                            value = student.value,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
                            },
                            modifier = Modifier.menuAnchor()
                                .width(750.dp)
                                .background(Color.White),
                            textStyle = TextStyle.Default.copy(
                                fontSize = 28.sp,
                                color = Color.Gray
                            ),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White)
                        )
                        ExposedDropdownMenu(
                            modifier = Modifier
                                .border(2.dp, Color.Gray, MaterialTheme.shapes.medium)
                                .background(Color.White),
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false },

                            ) {
                            listOfStudents.forEachIndexed { index, name ->
                                DropdownMenuItem(
                                    modifier = Modifier
                                        .width(850.dp),
                                    text = { Text(text = name, fontSize = 28.sp) },
                                    onClick = {
                                        if (index == 0) {
                                            selectedStudent.value = alumnos[0]
                                            student.value = selectedStudent.value.name ?: "Not selected"
                                            expanded.value = false
                                        }
                                        else {
                                            selectedStudent.value = alumnos[index - 1]
                                            student.value = selectedStudent.value.name ?: "Not selected"
                                            expanded.value = false
                                        }
                                    }
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(50.dp))

                    val context = LocalContext.current
                    Button(
                        shape = RectangleShape,
                        onClick = {
                            if(selectedStudent.value == alumnoDefault) {
                                ErrorDialog.show(context, "Seleccione un alumno para continuar")
                            } else {
                                val intent = Intent(context, SeleccionNivel::class.java)
                                intent.putExtra("studentId", selectedStudent.value.id)
                                intent.putExtra("studentName", selectedStudent.value.name)
                                intent.putExtra("MaximumNivelAcesso",selectedStudent.value.maximumMinigameLevel )
                                context.startActivity(intent)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(Orange),
                        ) {
                        Text("Continuar",
                            color = Color.White,
                            fontSize = 35.sp
                        )
                    }
                }
            }
        }
    }
}