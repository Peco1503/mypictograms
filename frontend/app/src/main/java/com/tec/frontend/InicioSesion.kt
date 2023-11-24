package com.tec.frontend

import android.app.AlertDialog
import android.content.ContentValues.TAG
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.tec.frontend.Api.RetrofitInstance
import com.tec.frontend.Api.loginRequest
import com.tec.frontend.Api.loginResponse
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject


class InicioSesion : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Inicio()
                    BackButtonPI()
                }
            }
        }
    }
}

@Composable
@Preview(name = "Landscape Mode", showBackground = true, device = Devices.PIXEL_C, widthDp = 1280)
fun Inicio() {
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var admin by remember { mutableStateOf(loginResponse(0, "", "")) }

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
                    .width(750.dp)
                    .height(550.dp)
                    .background(Color.White),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Inicio Sesión",
                        style = TextStyle(
                            fontSize = 65.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    BasicTextField(
                        modifier = Modifier
                            .width(700.dp)
                            .padding(top = 85.dp)
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
                    TextField(
                        modifier = Modifier
                            .width(700.dp)
                            .padding(top = 35.dp)
                            .border(2.dp, Color.Gray, MaterialTheme.shapes.medium)
                            .background(Color.White),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        ),
                            value = text2,
                        onValueChange = {
                            text2 = it
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 35.sp
                        ),
                        placeholder = { Text("Introduce tu contaseña", color = Color.Gray, fontSize = 35.sp) },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    Button(
                        modifier = Modifier
                            .padding(top = 45.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        onClick = {
                            coroutineScope.launch {
                                val response = withContext(Dispatchers.IO) {
                                    RetrofitInstance.apiService.login(
                                        loginRequest(user = text1, password = text2)
                                    )
                                }

                                if (response.isSuccessful) {
                                    admin = response.body()!!
                                    withContext(Dispatchers.Main) {
                                        val intent = Intent(context, DashboardProfe::class.java)
                                        intent.putExtra("AdminID", admin.id)
                                        context.startActivity(intent)
                                    }
                                } else {
                                    val jsonError = JSONObject(response.errorBody()!!.string())
                                    val errorMessage = jsonError.getString("error");

                                    Log.d(TAG, errorMessage)
                                    withContext(Dispatchers.Main) {
                                        // Create AlertDialog
                                        val alertDialogBuilder = AlertDialog.Builder(context)

                                        val titleTextView = TextView(context)
                                        titleTextView.text = "Error"
                                        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28f)
                                        titleTextView.setTextColor(
                                            ContextCompat.getColor(
                                                context,
                                                R.color.Red
                                            )
                                        )
                                        titleTextView.gravity =
                                            Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
                                        alertDialogBuilder.setCustomTitle(titleTextView)

                                        // Create a TextView to set the text size
                                        val textView = TextView(context)
                                        textView.text = errorMessage
                                        textView.setTextSize(
                                            TypedValue.COMPLEX_UNIT_SP,
                                            28f
                                        ) // Adjust the text size as needed
                                        textView.gravity =
                                            Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
                                        alertDialogBuilder.setView(textView)


                                        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
                                            dialog.dismiss()
                                        }

                                        val alertDialog = alertDialogBuilder.create()
                                        alertDialog.show()
                                    }
                                }
                            }
                        }
                    ) {
                        Text(
                            text = "Iniciar Sesión",
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




