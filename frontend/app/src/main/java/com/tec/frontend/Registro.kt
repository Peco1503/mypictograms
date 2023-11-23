package com.tec.frontend

import android.app.AlertDialog
import android.os.Bundle
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
    var text3 by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

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
                                Text(
                                    text = "Introduce tu nombre de usuario",
                                    color = Color.Gray,
                                    style = TextStyle(
                                        fontSize = 35.sp
                                    ),
                                    modifier = Modifier
                                        .padding(16.dp)
                                )
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
                                Text(
                                    text = "Introduce tu contraseña",
                                    color = Color.Gray,
                                    style = TextStyle(
                                        fontSize = 35.sp
                                    ),
                                    modifier = Modifier
                                        .padding(16.dp)
                                )
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
                    text3 = myadminorfather().toString()

                    Spacer(modifier = Modifier.padding(top = 25.dp))

                    Button(
                        modifier = Modifier
                            .padding(top = 15.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        onClick = {
                            coroutineScope.launch {
                                try {
                                        val user = text1
                                        val password = text2
                                        viewModel.registerUser(user, password, text3)
                                } catch (e : Exception) {
                                    val errorMessage = e.message.toString()
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
fun myadminorfather(): op {
    var selected by remember { mutableStateOf(op.admin) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RadioButton(
            selected = selected == op.admin,
            onClick = { selected = op.admin },
            modifier = Modifier.padding(5.dp)
        )
        Text("Admin", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))

        RadioButton(
            selected = selected == op.father,
            onClick = { selected = op.father },
            modifier = Modifier.padding(5.dp)
        )
        Text("Padre", style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp
        ))
    }

    return selected
}

enum class op {
    admin,
    father
}