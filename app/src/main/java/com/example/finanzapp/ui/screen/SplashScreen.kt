package com.example.finanzapp.ui.screen

import android.provider.CalendarContract
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.finanzapp.R
import kotlinx.coroutines.delay


@Composable
@Preview
fun SplashScreenPreview (){
 //SplashScreen()
}

@Composable
fun SplashScreen(
    navigation: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF021470))
){
    LaunchedEffect(key1 = true) {
        delay(5000)
        navigation.popBackStack()
        navigation.navigate("login")
    }
    val gradient = Brush.linearGradient(
        arrayListOf(Color(0xFF124A73),Color(0xFF001D34))
    )

    Box(
        modifier = modifier.background(gradient),
        contentAlignment = Alignment.Center
    ) {

        Image(

            painter = painterResource(R.drawable.logo_1),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }




}






