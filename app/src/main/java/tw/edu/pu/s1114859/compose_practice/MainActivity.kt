package tw.edu.pu.s1114859.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
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
                   // Greeting("Compose實例")
                    Animation()

                }
            }
        }
    }
}

@Composable
fun Animation(){
    var appear by  remember{ mutableStateOf(true) }//背景出現
    var expanded by remember{ mutableStateOf(true) }//背景延展
    var fly by remember { mutableStateOf(false) }//火箭升空
    //角度動畫
    val buttonAngle by animateFloatAsState(
        if (appear)360f else 0f,
        animationSpec = tween(durationMillis = 2500)
    )
    //顏色動畫
    val backgroundColor by animateColorAsState(
        if(appear)Color.Transparent else Color.Green,
        animationSpec = tween(2000,500)
    )
    //大小動畫
    val rocketSize by animateDpAsState(
        if (fly) 75.dp else 150.dp,
        animationSpec = tween(2000)
    )
    val rocketOffset by animateOffsetAsState(
        if(fly) Offset(200f,50f) else Offset(200f,400f),
        animationSpec = tween(2000)
    )
    Column(Modifier.background(backgroundColor)) {
        Button(
            onClick = {appear=!appear},
            modifier = Modifier.rotate(buttonAngle)
        ){
            if(appear) Text(text = "星空背景圖消失")
            else Text(text = "星空背景圖出現")
        }
        AnimatedVisibility(
            visible = appear,
            enter = fadeIn(
                initialAlpha = 0.1f,
                animationSpec = tween(durationMillis = 5000))
            + slideInHorizontally (
                animationSpec = tween(durationMillis = 5000)){fullWidth ->
                fullWidth/3
            },
            exit = fadeOut(animationSpec = tween(durationMillis = 5000))
            + slideOutHorizontally(
                animationSpec = tween(durationMillis = 5000){fullWidth->
                    -fullWidth/3
                }
            )
        ){
            Image(painter = painterResource(id = R.drawable.sky),
                contentDescription = "星空背景圖",
            modifier= Modifier
                .animateContentSize()
                .fillMaxWidth()
                .height(if (expanded) 600.dp else 400.dp)
                .clickable(
                ) {
                    expanded = !expanded
                }
            )
            Image(painter = painterResource(id = R.drawable.rocket),
                contentDescription ="火箭",
                modifier= Modifier
                    .size(rocketSize)
                    .offset(rocketOffset.x.dp,rocketOffset.y.dp)
                    .clickable {
                        fly = !fly
                    })
        }

    }
}