package com.tec.frontend

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.tec.frontend.Api.Category
import com.tec.frontend.Api.Images
import com.tec.frontend.Api.RetrofitInstance
import com.tec.frontend.ui.theme.FrontendTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComunicadorCategories : ComponentActivity() {
    private var studentId: Int = -1
    private var studentName: String = " "
    private var categoryName: String = " "
    private var imagePhrase: MutableList<Images> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                studentId = intent.getIntExtra("studentId", -1)
                studentName = intent.getStringExtra("studentName").toString()
                categoryName = intent.getStringExtra("categoryName").toString()
                imagePhrase = intent.getParcelableExtra("imagePhrase") as? MutableList<Images> ?: mutableListOf()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF4169CF)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        BackButtonImages(studentId, studentName, imagePhrase)
                        ImageGridCategories(studentId, studentName, imagePhrase)
                    }
                }
            }
        }
    }
}

@Composable
fun BackButtonImages(studentId: Int, studentName : String, imagePhrase : MutableList<Images>) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.Top) {
        val context = LocalContext.current
        Button(
            shape = RectangleShape,
            onClick = {
                val intent = Intent(context, Comunicador::class.java)
                intent.putExtra("studentId", studentId)
                intent.putExtra("studentName", studentName)
                intent.putExtra("imagePhrase", ArrayList(imagePhrase))
                context.startActivity(intent)
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

@Composable
fun ImageGridCategories(studentId: Int, categoryName: String, imagePhrase : MutableList<Images>) {
    var images by remember { mutableStateOf<List<Images>>(emptyList()) }

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                // Make Retrofit API call on the background thread
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.apiService.getComunicadorCategory(categoryName, studentId)
                }
                images = response

            } catch (e: Exception) {
                Log.d(ContentValues.TAG, e.toString())
            }
        }
    }


    var imageLabels = listOf<String>()
    var imageIds = listOf<String>()
    //var destinations = listOf<String>()

    // Variables para Category
    //var listOfCategoryNames = arrayOf("")
    images.forEach { categorie ->
        imageLabels = imageLabels + categorie.name.toString()
        imageIds = imageIds + categorie.url.toString()
    }


    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(imageIds.size) { index ->
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = imageIds[index],
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = imageLabels[index],
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            imagePhrase.add(index, images[index])
                        }
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                ) {
                    Text(
                        text = imageLabels[index],
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            letterSpacing = 1.5.sp
                        ),
                        color = Color.White
                    )
                }
            }
        }
    }
}