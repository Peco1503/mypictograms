package com.tec.frontend

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.Api.RetrofitInstance
import com.tec.frontend.Api.registerRequest
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject


class Registro : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Registros(activityContext = this)
                }
            }
        }
    }
}

@Composable
fun Registros(activityContext: Registro) {
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var text3 by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

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
                    .width(700.dp)
                    .background(Color.White),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier.padding(50.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Registro", style = TextStyle(
                            fontSize = 65.sp, fontWeight = FontWeight.Bold
                        )
                    )
                    TextField(
                        shape = RoundedCornerShape(0.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp)
                            .border(2.dp, Color.Gray),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        ),
                        value = text1,
                        onValueChange = {
                            text1 = it
                        },
                        textStyle = TextStyle(
                            color = Color.Black, fontSize = 35.sp
                        ),
                        placeholder = {
                            Text(
                                "Ingrese su usuario...", color = Color.Gray, fontSize = 35.sp
                            )
                        },
                    )
                    TextField(
                        shape = RoundedCornerShape(0.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp)
                            .border(2.dp, Color.Gray),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        ),
                        value = text2,
                        onValueChange = {
                            text2 = it
                        },
                        textStyle = TextStyle(
                            color = Color.Black, fontSize = 35.sp
                        ),
                        placeholder = {
                            Text(
                                "Ingrese su contraseña...", color = Color.Gray, fontSize = 35.sp
                            )
                        },
                    )
                    Row (
                        modifier = Modifier.fillMaxWidth()
                    )
                    {
                        Text(
                            modifier = Modifier.padding(top=20.dp),
                            text = "Nota: la contraseña debe:\n" + " * Ser de 12 caracteres o más\n" + " * Incluir al menos un número\n" + " * Incluir al menos una mayúscula\n" + " * Incluir al menos una minúscula\n" + " * Incluir al menos un caracter especial (~`!@#\$%^&*... etc)",
                            fontSize = 20.sp
                        )
                    }

                    text3 = myadminorfather().toString()

                    Button(modifier = Modifier.padding(top = 15.dp),
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        onClick = {
                            when (text3) {
                                op.admin.toString() -> {
                                    coroutineScope.launch {
                                        val response = withContext(Dispatchers.IO) {
                                            RetrofitInstance.apiService.createAdmin(
                                                registerRequest(user = text1, password = text2)
                                            )
                                        }

                                        if (!response.isSuccessful) {
                                            val jsonError =
                                                JSONObject(response.errorBody()!!.string())
                                            val errorMessage = jsonError.getString("error");

                                            Log.d(TAG, errorMessage)
                                            withContext(Dispatchers.Main) {
                                                ErrorDialog.show(context, errorMessage)
                                            }
                                        } else {
                                            activityContext.finish()
                                        }
                                    }
                                }

                                op.father.toString() -> {
                                    coroutineScope.launch {
                                        val response = withContext(Dispatchers.IO) {
                                            RetrofitInstance.apiService.createParent(
                                                registerRequest(user = text1, password = text2)
                                            )
                                        }

                                        if (!response.isSuccessful) {
                                            val jsonError =
                                                JSONObject(response.errorBody()!!.string())
                                            val errorMessage = jsonError.getString("error");

                                            Log.d(TAG, errorMessage)
                                            withContext(Dispatchers.Main) {
                                                ErrorDialog.show(context, errorMessage)
                                            }
                                        } else {
                                            activityContext.finish()
                                        }
                                    }
                                }

                                else -> {
                                    Log.d(TAG, "ERROR: neither admin nor parent were selected")
                                }
                            }
                        }

                    ) {
                        Text(
                            text = "Registrar", style = TextStyle(
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
fun myadminorfather(): op {
    var selected by remember { mutableStateOf(op.admin) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top=15.dp)
    ) {
        RadioButton(
            selected = selected == op.admin,
            onClick = { selected = op.admin },
            modifier = Modifier.padding(5.dp)
        )
        Text("Terapeuta", style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 35.sp))

        RadioButton(
            selected = selected == op.father,
            onClick = { selected = op.father },
            modifier = Modifier.padding(5.dp)
        )
        Text(
            "Padre", style = TextStyle(
                fontWeight = FontWeight.Medium, fontSize = 35.sp
            ),
            modifier = Modifier.padding(end=20.dp),
        )
    }

    return selected
}

enum class op {
    admin, father
}