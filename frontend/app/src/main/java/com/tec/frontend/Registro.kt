package com.tec.frontend

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tec.frontend.Api.registerRequest
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.launch

class Registro : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Registros()
                }
            }
        }
    }
}

@Composable
fun Registros() {
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var option1CheckedState by remember { mutableStateOf(false) }
    var option2CheckedState by remember { mutableStateOf(false) }

    // ViewModel instance
    val viewModel: RegistroViewModel = viewModel()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF4169CF),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(800.dp)
                    .width(600.dp)
                    .background(Color.White),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Registro",
                        style = TextStyle(
                            fontSize = 65.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    BasicTextField(
                        modifier = Modifier
                            .width(550.dp)
                            .padding(top = 45.dp)
                            .border(2.dp, Color.Gray, MaterialTheme.shapes.medium),
                        value = text1,
                        onValueChange = {
                            text1 = it
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 35.sp
                        ),
                        decorationBox = { innerTextField ->
                            if (text1.isEmpty()) {
                                Text(
                                    text = "Introduce tu nombre de usuario",
                                    color = Color.Gray,
                                    style = TextStyle(
                                        fontSize = 35.sp
                                    ),
                                    modifier = Modifier
                                        .padding(16.dp)
                                )
                            }
                            innerTextField()
                        }
                    )
                    BasicTextField(
                        modifier = Modifier
                            .width(550.dp)
                            .padding(top = 35.dp)
                            .border(2.dp, Color.Gray, MaterialTheme.shapes.medium),
                        value = text2,
                        onValueChange = {
                            text2 = it
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 35.sp
                        ),
                        decorationBox = { innerTextField ->
                            if (text2.isEmpty()) {
                                Text(
                                    text = "Introduce tu contraseña",
                                    color = Color.Gray,
                                    style = TextStyle(
                                        fontSize = 35.sp
                                    ),
                                    modifier = Modifier
                                        .padding(16.dp)
                                )
                            }
                            innerTextField()
                        }
                    )
                    Spacer(modifier = Modifier.padding(top = 25.dp))
                    Column {
                        Text(
                            text = "Nota: la contraseña debe:\n" +
                                    " * Ser de 12 caracteres o más\n" +
                                    " * Incluir al menos un número\n" +
                                    " * Incluir al menos una mayúscula\n" +
                                    " * Incluir al menos una minúscula\n" +
                                    " * Incluir al menos un caracter especial (~`!@#\$%^&*... etc)"
                        )
                    }

                    Spacer(modifier = Modifier.padding(top = 25.dp))

                    TwoOptionsCheckBox(
                        option1CheckedState = option1CheckedState,
                        option2CheckedState = option2CheckedState,
                        onOption1CheckedChange = { option1CheckedState = it },
                        onOption2CheckedChange = { option2CheckedState = it }
                    )

                    val context = LocalContext.current

                    Button(
                        modifier = Modifier
                            .padding(top = 15.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        onClick = {
                            val user = text1
                            val password = text2

                            if (option1CheckedState || option2CheckedState) {
                                val userType = if (option1CheckedState) "admin" else "padre"

                                // Perform registration using ViewModel
                                viewModel.registerUser(user, password, userType)
                            } else {
                                // Show an error or prompt the user to select a user type
                            }
                        }
                    ) {
                        Text(
                            text = "Registrar",
                            style = TextStyle(
                                fontSize = 35.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TwoOptionsCheckBox(
    option1CheckedState: Boolean,
    option2CheckedState: Boolean,
    onOption1CheckedChange: (Boolean) -> Unit,
    onOption2CheckedChange: (Boolean) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Titulo     ", style = TextStyle(fontSize = 35.sp), modifier = Modifier.padding(25.dp))
            Text(text = "Admin", style = TextStyle(fontSize = 35.sp), modifier = Modifier.padding(25.dp))
            Checkbox(
                checked = option1CheckedState,
                onCheckedChange = { onOption1CheckedChange(it) },
                modifier = Modifier
                    .weight(1f)
                    .height(83.dp)
                    .padding(25.dp)
            )
            Text(text = "Padre", style = TextStyle(fontSize = 35.sp), modifier = Modifier.padding(25.dp))
            Checkbox(
                checked = option2CheckedState,
                onCheckedChange = { onOption2CheckedChange(it) },
                modifier = Modifier
                    .weight(1f)
                    .padding(25.dp)
            )
        }
    }
}
