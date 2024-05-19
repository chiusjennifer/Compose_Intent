package tw.edu.pu.s1114859.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import tw.edu.pu.s1114859.compose_practice.ui.theme.Compose_practiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_practiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Compose實例")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var msg by remember { mutableStateOf("行動應用軟體開發") }
    Column {
        Button(onClick = {
            if (msg=="行動應用軟體開發"){
                msg="Android App"
            }else{
                msg="行動應用軟體開發"
            }
        }) {
            Text(text = msg)
        }
        Button(onClick = {},
            colors=buttonColors(Color.DarkGray),
            shape = RoundedCornerShape(20.dp),
            //shape = CutCornerShape(10)
            border= BorderStroke(1.dp, Color.Blue),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            )
        ) {
            Image(
                painterResource(id = R.drawable.animal0),
                contentDescription = "button icon",
                modifier=Modifier.size(20.dp)
            )
            Text(
                text = "Click",
                color=Color.Magenta
            )
            Text(text = "Here",
                color = Color.Green
            )
        }
        Row {
            Text(
                text = "$name",
                fontFamily = FontFamily(Font(R.font.kai)),
                fontSize = 25.sp,
                color = Color.Blue,
                modifier = modifier
            )
            Image(
                painter = painterResource(id = R.drawable.compose),
                contentDescription = "圖片",
                alpha = 0.7f,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Black)
            )
        }
        Box(
            modifier=Modifier.fillMaxSize(),
            contentAlignment =Alignment.Center
        ){
            var count by remember { mutableStateOf(0) }
            Text(
                text = count.toString(),
                fontSize = 50.sp,
                modifier= Modifier.clickable { count+=1 }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose_practiceTheme {
        Greeting("Compose實例")
    }
}