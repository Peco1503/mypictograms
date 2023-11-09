package com.tec.frontend

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tec.frontend.ui.theme.FrontendTheme
import androidx.compose.foundation.layout.Column as Column
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*

class EditAlumno : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Edit()
                }
            }
        }
    }
}

@Composable
fun Edit()
{
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
                    .width(650.dp)
                    .height(750.dp)
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
                        EditText()
                    }
                    Row(modifier = Modifier.padding(top=16.dp)) {
                        Text(text = "Edad:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        EditText()
                    }
                    Row(modifier = Modifier.padding(top=16.dp)) {
                        Text(text = "Genero:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        EditText()
                    }
                    Row(modifier = Modifier.padding(top=16.dp)) {
                        Text(text = "Tutor:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        EditText()
                    }
                    FourOptionsCheckBox()
                    Row(modifier = Modifier.padding(top=16.dp)){
                        Text(text = "Descripción:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        EditText()
                    }
                    Row(modifier = Modifier.padding(top=16.dp)){
                        Text(text = "Nivel Cognitivo:  ", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp))
                        EditText()
                    }
                    val context1 = LocalContext.current
                    val context2 = LocalContext.current
                    Row(modifier = Modifier.padding(top=16.dp), horizontalArrangement = Arrangement.SpaceBetween){
                        Button(onClick = { context1.startActivity(Intent(context1, InfoAlumno::class.java)) },
                            modifier = Modifier
                                .padding(15.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEE6B11)),
                        ) {
                            Text(text = "Cancelar", style = TextStyle(fontSize = 35.sp))
                        }
                        Button(onClick = { context2.startActivity(Intent(context2, EditAlumno::class.java)) },
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
private fun EditText() {
    var text by remember { mutableStateOf(TextFieldValue()) }
    var isPlaceholderVisible by remember { mutableStateOf(true) }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
            isPlaceholderVisible = it.text.isEmpty()
        },
        textStyle = TextStyle(color = Color.Black, fontSize = 35.sp),
        modifier = Modifier
            .width(350.dp)
            .border(2.dp, Color.Gray, MaterialTheme.shapes.medium)
    )
    {
        if (isPlaceholderVisible) {
            Text(
                text = "Ingrese Información",
                style = TextStyle(fontSize = 35.sp),
                color = Color.Gray,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}