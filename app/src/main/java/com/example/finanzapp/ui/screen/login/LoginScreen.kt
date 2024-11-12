package com.example.finanzapp.ui.screen.login

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.finanzapp.R
import com.example.finanzapp.database.AppDatabase
import com.example.finanzapp.ui.components.BarraSuperior
import com.example.finanzapp.ui.components.CategoriaItem
import com.example.finanzapp.ui.components.DateTextField

@Composable
@Preview
fun LoginScreenPreview (){
    val navigation = rememberNavController()
    LoginScreen(navigation = navigation)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navigation: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFCFE5FF))
){
    Column(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF124A73), Color(0xFF001D34))
                )
            )
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(
            painter = painterResource(id = R.drawable.logo_banner),
            contentDescription = "Finanz App",
            tint = Color(0xFFCFE5FF),
            modifier = Modifier.height(40.dp).fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(10.dp)
        ){

            Text(
                text = "Log In",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField (
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFFCFE5FF)),
                modifier = Modifier
                    .fillMaxWidth(),
                value = "",
                onValueChange ={
//                if (it.length <= 20){
//                    movimientoViewModel.setNombre(it)
//                }
                },
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
                label =  { Text(text = "Correo Electrónico" )},
                trailingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_limpiar), contentDescription = "")
                },
                maxLines = 1,
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField (
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFFCFE5FF)),
                modifier = Modifier
                    .fillMaxWidth(),
                value = "",
                onValueChange ={
//                if (it.length <= 20){
//                    movimientoViewModel.setNombre(it)
//                }
                },
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
                label =  { Text(text = "Contraseña" )},
                trailingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_log_trail), contentDescription = "")
                },
                maxLines = 1,
                singleLine = true
            )

            HyperlinkText(text = "¿Olvidaste la contraseña?", modifier = Modifier.align(alignment = Alignment.End)) {  }

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .fillMaxWidth(.7f),
                colors = ButtonDefaults.buttonColors().copy(containerColor = Color(0xff31628D))
                ,
                onClick = {
                    navigation.navigate("home")
                }
            ) {
                Text(text = "Iniciar Sesión")
            }
            
            Spacer(modifier = Modifier.height(25.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "¿No tienes cuenta?")
                Spacer(modifier = Modifier.height(4.dp))
                HyperlinkText(text = "Registrate") {  }

            }
        }
    }
}


@Composable
fun HyperlinkText(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val annotatedString = with(AnnotatedString.Builder()) {
        pushStringAnnotation(
            tag = "LINK",
            annotation = onClick.toString()
        )
        append(text)
        pop()
        toAnnotatedString()
    }

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = "LINK",
                start = offset,
                end = offset
            ).firstOrNull()?.let {
                onClick()
            }
        },
        style = TextStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)
    )
}