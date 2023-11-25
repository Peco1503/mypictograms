package com.tec.frontend

import android.app.AlertDialog
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
                    New()
                }
            }
        }
    }
}

@Composable
@Preview(name = "Landscape Mode", showBackground = true, device = Devices.PIXEL_C, widthDp = 1280)
fun New() {
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
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(shape = RoundedCornerShape(0.dp),
                        modifier = Modifier
                            .padding(top = 50.dp)
                            .border(2.dp, Color.Gray)
                            .background(Color.White),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        ),
                        value = text1,
                        onValueChange = {
                            text1 = it
                        },
                        textStyle = TextStyle(
                            color = Color.Black, fontSize = 65.sp
                        ),
                        placeholder = {
                            Text(
                                "Inserte nombre...", color = Color.Gray, fontSize = 65.sp
                            )
                        })
                    Row(modifier = Modifier.padding(top = 16.dp)) {
                        Text1(
                            text = "Año de nacimiento:  ",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp)
                        )
                        EditText(text2) { newText -> text2 = newText }
                    }
                    Row(modifier = Modifier.padding(top = 16.dp)) {
                        Text1(
                            text = "Género:  ",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp)
                        )
                        text3 = myGenderSelection().toString()
                    }
                    Row(modifier = Modifier.padding(top = 16.dp)) {
                        Text1(
                            text = "ID Tutor:  ",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp)
                        )
                        EditText(text4) { newText -> text4 = newText }
                    }
                    text7 = fourOptionsCheckBox()
                    Row(modifier = Modifier.padding(top = 16.dp)) {
                        Text1(
                            text = "Descripción:  ",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp)
                        )
                        EditText(text5) { newText -> text5 = newText }
                    }
                    Row(modifier = Modifier.padding(top = 16.dp)) {
                        Text1(
                            text = "Nivel Cognitivo:  ",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp)
                        )
                        EditText(text6) { newText -> text6 = newText }
                    }
                    val context1 = LocalContext.current
                    Row(
                        modifier = Modifier.padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                val intent = Intent(context1, DashboardProfe::class.java)
                                //intent.putExtra("AdminID", adminId)
                                context1.startActivity(intent)
                            },
                            modifier = Modifier.padding(15.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        ) {
                            Text1(text = "Atrás", style = TextStyle(fontSize = 35.sp))
                        }

                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    try {
                                        Log.d(
                                            "New",
                                            "Nombre: $text1, Edad: $text2, Genero: $text3, Tutor: $text4, Descripción: $text5, Cognitivo: $text6, Nivel: $text7 Id tERAPIA: "
                                        )
                                        // viewModel.registerAlumno(
                                        //    text1,
                                        //    text2.toInt(),
                                        //    text3,
                                        //    if (text4 == "") null else text4.toInt(),
                                        //    text7,
                                        //    text5,
                                        //    text6,
                                        //    adminId
                                        // )
                                        val intent = Intent(context1, DashboardProfe::class.java)
                                        //intent.putExtra("AdminID", adminId)
                                        context1.startActivity(intent)
                                    } catch (e: Exception) {
                                        val errorMessage = e.message.toString()

                                        withContext(Dispatchers.Main) {
                                            // Create AlertDialog
                                            val alertDialogBuilder = AlertDialog.Builder(context)

                                            val titleTextView = TextView(context)
                                            titleTextView.text = "Error"
                                            titleTextView.setTextSize(
                                                TypedValue.COMPLEX_UNIT_SP, 28f
                                            )
                                            titleTextView.setTextColor(
                                                ContextCompat.getColor(
                                                    context, R.color.Red
                                                )
                                            )
                                            titleTextView.gravity =
                                                Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
                                            alertDialogBuilder.setCustomTitle(titleTextView)

                                            // Create a TextView to set the text size
                                            val textView = TextView(context)
                                            textView.text = errorMessage
                                            textView.setTextSize(
                                                TypedValue.COMPLEX_UNIT_SP, 28f
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
                            },
                            modifier = Modifier.padding(15.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        ) {
                            Text1(text = "Agregar Alumno", style = TextStyle(fontSize = 35.sp))
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
    OutlinedTextField(value = txt,
        onValueChange = {
            onTextChange(it)
        },
        textStyle = TextStyle(color = Color.Black, fontSize = 28.sp),
        modifier = Modifier
            .width(350.dp)
            .height(60.dp)
            .border(2.dp, Color.Gray, MaterialTheme.shapes.medium),
        placeholder = {
            Text1(
                "Ingrese Informacion", style = TextStyle(fontSize = 23.sp), color = Color.Gray
            )
        })
}

@Composable
private fun fourOptionsCheckBox(): Int {
    var selectedOption by remember { mutableStateOf<Option?>(null) }
    var num: Int = 0

    Column {
        Text1(
            text = "Nivel Autorizado: ",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp),
            modifier = Modifier.padding(5.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
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
        modifier = Modifier
            .padding(25.dp)
            .clickable {
                if (selectedOption != option) {
                    onOptionSelected(option)
                }
            }) {
        Text1(text = text, style = TextStyle(fontSize = 35.sp))
        RadioButton(selected = selectedOption == option, onClick = { onOptionSelected(option) })
    }
}

@Composable
fun myGenderSelection(): Gender {
    var selectedGender by remember { mutableStateOf(Gender.male) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RadioButton(
            selected = selectedGender == Gender.male,
            onClick = { selectedGender = Gender.male },
            modifier = Modifier.padding(5.dp)
        )
        Text1("Male", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))

        RadioButton(
            selected = selectedGender == Gender.female,
            onClick = { selectedGender = Gender.female },
            modifier = Modifier.padding(5.dp)
        )
        Text1(
            "Female", style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 35.sp
            )
        )
    }

    return selectedGender
}

enum class Gender {
    male, female
}

