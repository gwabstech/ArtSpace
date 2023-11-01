package com.gwabs.artspace

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gwabs.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}






    

data class ImagesData(val title:String, val description: String)


@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {

    var result by remember { mutableStateOf(1) }
    var image by remember {
        mutableStateOf(1)
    }
     image = when(result){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }



    val imageData: ImagesData = when(result){
        1 -> ImagesData("Example 1 Art", "Example 1")
        2 -> ImagesData("Example 2 Art", "Example 2")
        3 -> ImagesData("Example 3 Art", "Example 3")
        4 -> ImagesData("Example 4 Art", "Example 4")

        else -> {
            throw TypeCastException("Hi")
        }
    }


    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom

    ){
        Spacer(modifier = modifier.height(30.dp))
        ArtworkWall(image = image)
        Spacer(modifier = modifier.height(30.dp))
        ArtworkDescriptor(title = imageData.title, author = imageData.description)
        Spacer(modifier = modifier.height(30.dp))
        DisplayController(onPrevious = {
            if (result <=1){
                result = 4
            }else {
                result -= 1
            }
            Log.i("TAG",result.toString())
        }) {
            if (result < 4){
                result +=1
            }else if (result >= 4){
                result =1

            }
            Log.i("TAG",result.toString())
            
       }
        Spacer(modifier = modifier.height(150.dp))
    }
}

@Composable
fun ArtworkWall(modifier: Modifier = Modifier,image:Int) {


    Surface(
        shape = RoundedCornerShape(6.dp),
        shadowElevation = 10.dp,
        tonalElevation = 10.dp,
        modifier = modifier.height(300.dp),
        color =   Color.White

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .width(400.dp)
                .padding(20.dp)
                .background(Color(0xffd6ffe3)),
            contentAlignment = Alignment.Center,
        ) {

            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = modifier.padding(20.dp),
            )

        }
    }
}

@Composable
fun ArtworkDescriptor(title: String, author: String, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .background(color = Color(0xFFEEF7F1), shape = RoundedCornerShape(5.dp))
            .fillMaxWidth()
            .height(100.dp),
        verticalArrangement = Arrangement.Center,
    ) {

        Text(
            text = title,
            modifier = modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp

        )
        Text(
            text = author,
            modifier = modifier.padding(10.dp),
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp

        )
    }
}

@Composable
fun DisplayController(modifier: Modifier = Modifier, onPrevious: () -> Unit, onNext: () -> Unit) {

    Row (
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){

        ElevatedButton(
            onClick = {onPrevious() },
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color(0xFF327CEB),
                contentColor = MaterialTheme.colorScheme.background
            ),

        ) {
            Text(
                text = stringResource(R.string.previous),
                style = MaterialTheme.typography.bodyLarge

            )
        }

        ElevatedButton(
            onClick = {onNext() },
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color(0xFF327CEB),
                contentColor = MaterialTheme.colorScheme.background
            ),

        ) {
            Text(
                text = stringResource(R.string.next),
                style = MaterialTheme.typography.bodyLarge

            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
       // val title = R.string.text1
       // val autor = R.string.text2
       // ArtworkDescriptor(title, autor)
      // DisplayController(onPrevious = { /*TODO*/ }) {
            
     //  }
    }
}