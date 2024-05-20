package tw.edu.pu.s1114859.compose_practice

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    //FirstScreen()
                   // SecondScreen()
                    Main()
                }
            }
        }
    }
}

@Composable
fun FirstScreen(navController:NavController){
    val context= LocalContext.current //取得app的運行環境
    var url by remember { mutableStateOf("https://www.pu.edu.tw") }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        TextField(
           value = url,
            onValueChange = {newText->
                url=newText
            },
            label = { Text(text = "網址")},
            placeholder = {
                Text(text = "請輸入您要瀏覽的網站") }
        )
        Button(onClick = {
            var it=Intent(context,SecondActivity::class.java)
            it.putExtra("website",url)
            context.startActivity(it)
        }) {
            Text(text = "跳轉到SecondActivity")
        }
        Button(onClick = {
            var it=Intent(Intent.ACTION_VIEW)
            it.data= Uri.parse(url)
            context.startActivity(it)
        }) {
           Text(text = "開啟瀏覽器")
        }
        Button(onClick = {
            var it =Intent(Intent.ACTION_SENDTO)
            it.data=Uri.parse("mailto:tcyang@gm.pu.edu.tw")
            context.startActivity(it)
        }) {
            Text(text = "寄發電子郵件")
        }
        Button(
            onClick = {
                var it = Intent(Intent.ACTION_WEB_SEARCH)
                it.putExtra(SearchManager.QUERY,"靜宜資管")
                context.startActivity(it)
            }
        )
        {
            Text(text = "搜尋關鍵字")
        }
        Button(onClick = {
            var it=Intent(Intent.ACTION_VIEW)
            it.data=Uri.parse("geo:24.2267756,120.5771591")
            context.startActivity(it)
        }) {
         Text(text = "Google Map查詢")
        }
        Button(onClick = {
            navController.navigate("JumpSecond")
        }) {
        Text(text = "我是畫面1，按一下跳至畫面2")
        }
    }
}
@Composable
fun SecondScreen(navController: NavController){
    Column(modifier= Modifier
        .fillMaxSize()
        .background(Color.Yellow),
        verticalArrangement=Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { 
            navController.navigate("JumpFirst")
        }) {
            Text(text = "我是畫面2，按一下跳至畫面1")
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(){
    val navController= rememberNavController()
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false)}
    Column {
        TopAppBar(
            title = { Text(text = "頁面轉換實例") },
            navigationIcon = {
                IconButton(onClick = {
                    Toast.makeText(context,"您點選了導覽圖示",Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Default.Menu, contentDescription = "Navigation icon")
                }
            },
            actions = {
                IconButton(
                    onClick = {Toast.makeText(context,"作者:楊子青",Toast.LENGTH_SHORT).show() }
                ){
                 Icon(Icons.Rounded.AccountBox, contentDescription = "Author")
                }
                IconButton(onClick = { showMenu=true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More")
                }
                DropdownMenu(
                    expanded = showMenu, onDismissRequest = {showMenu=false}
                ) {
                    DropdownMenu(
                        expanded = showMenu, onDismissRequest = { showMenu=false }
                    ){
                        DropdownMenuItem(text = {Text("畫面1")},
                            onClick = {navController.navigate("JumpFirst")})
                        DropdownMenuItem(text = {Text("畫面2")},
                            onClick = {navController.navigate("JumpSecond")})
                    }
                }
            })

        NavHost(navController = navController, startDestination = "JumpFirst") {
            composable("JumpFirst") {
                FirstScreen(navController = navController)
            }
            composable("JumpSecond") {
                SecondScreen(navController = navController)
            }
        }
    }
}