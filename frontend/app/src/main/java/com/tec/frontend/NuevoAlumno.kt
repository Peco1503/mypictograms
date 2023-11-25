package com.tec.frontend

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tec.frontend.Api.Alumno
import com.tec.frontend.Api.ApiService
import com.tec.frontend.Api.RetrofitInstance
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import androidx.compose.material3.Text as Text1


class NuevoAlumno : ComponentActivity() {
    private var AdminId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                AdminId = intent.getIntExtra("AdminID", -1)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    New(adminId = AdminId)
                }
            }
        }
    }
}

@Composable
//@Preview(name = "Landscape Mode", showBackground = true, device = Devices.PIXEL_C, widthDp = 1280)
fun New(adminId: Int) {
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var text3 by remember { mutableStateOf("") }
    var text4 by remember { mutableStateOf("") }
    var text5 by remember { mutableStateOf("") }
    var text6 by remember { mutableStateOf("") }
    var text7 by remember { mutableStateOf(0) }
    val viewModel: RegistroViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
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
                    .background(Color.White),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(40.dp)
                ) {
                    TextField(shape = RoundedCornerShape(0.dp),
                        modifier = Modifier.border(2.dp, Color.Gray),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        ),
                        value = text1,
                        onValueChange = {
                            text1 = it
                        },
                        textStyle = TextStyle(
                            color = Color.Black, fontSize = 50.sp
                        ),
                        placeholder = {
                            Text(
                                "Inserte nombre...", color = Color.Gray, fontSize = 50.sp
                            )
                        })
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
                            text = "ID Tutor: ",
                            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 32.sp)
                        )
                        TextField(shape = RoundedCornerShape(0.dp),
                            modifier = Modifier.border(2.dp, Color.Gray),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White
                            ),
                            value = text4,
                            onValueChange = {
                                text4 = it
                            },
                            textStyle = TextStyle(
                                color = Color.Black, fontSize = 32.sp
                            ),
                            placeholder = {
                                Text(
                                    "Inserte id tutor...", color = Color.Gray, fontSize = 32.sp
                                )
                            })
                    }
                    text7 = fourOptionsCheckBox()
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
                                val intent = Intent(context1, DashboardProfe::class.java)
                                intent.putExtra("AdminID", adminId)
                                context1.startActivity(intent)
                            },
                            shape = RoundedCornerShape(0.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                            modifier = Modifier.padding(end = 20.dp)
                        ) {
                            Text(text = "Cancelar", style = TextStyle(fontSize = 32.sp))
                        }

                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    Log.d(
                                        "New",
                                        "Nombre: $text1, Edad: $text2, Genero: $text3, Tutor: $text4, Descripción: $text5, Cognitivo: $text6, Nivel: $text7 Id tERAPIA: "
                                    )
                                    val response = withContext(Dispatchers.IO) {
                                        RetrofitInstance.apiService.insertalumno(
                                            alumno = Alumno(
                                                null,
                                                text1,
                                                if (text2 == "") null else text2.toInt(),
                                                text3,
                                                if (text4 == "") null else text4.toInt(),
                                                text7,
                                                text5,
                                                text6,
                                                adminId
                                            )
                                        )
                                    }

                                    if (response.isSuccessful) {
                                        val intent = Intent(context1, DashboardProfe::class.java)
                                        intent.putExtra("AdminID", adminId)
                                        context1.startActivity(intent)
                                    } else {
                                        val jsonError = JSONObject(response.errorBody()!!.string())
                                        val errorMessage = jsonError.getString("error");

                                        Log.d(ContentValues.TAG, errorMessage)
                                        withContext(Dispatchers.Main) {
                                            ErrorDialog.show(context, errorMessage)
                                        }
                                    }
                                }
                            },
                            shape = RoundedCornerShape(0.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        ) {
                            Text(text = "Crear", style = TextStyle(fontSize = 32.sp))
                        }
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun fourOptionsCheckBox(): Int {
    var selectedOption by remember { mutableStateOf<Option?>(null) }
    var num: Int = 0

    Row(
        modifier = Modifier.padding(top = 20.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Nivel Autorizado:",
            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 32.sp),
            modifier = Modifier.padding(end = 10.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            OptionRadioButton("1",
                Option.OPTION1,
                selectedOption,
                onOptionSelected = { selectedOption = it })
            OptionRadioButton("2",
                Option.OPTION2,
                selectedOption,
                onOptionSelected = { selectedOption = it })
            OptionRadioButton("3",
                Option.OPTION3,
                selectedOption,
                onOptionSelected = { selectedOption = it })
            OptionRadioButton("4",
                Option.OPTION4,
                selectedOption,
                onOptionSelected = { selectedOption = it })
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
