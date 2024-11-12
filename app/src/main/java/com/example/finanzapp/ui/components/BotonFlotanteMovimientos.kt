package com.example.finanzapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finanzapp.R
import com.example.finanzapp.models.Categoria

@Composable
@Preview
fun BotonFlotanteMovimientosPreview(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red),
        contentAlignment = Alignment.Center
    ){
        BotonFlotanteMovimientos(onIngreso = {}, onGasto = {}, modifier = Modifier)
    }
}

@Composable
fun BotonFlotanteMovimientos(onIngreso: () -> Unit, onGasto: () -> Unit, modifier: Modifier){
    var mostrarDropdownmovimiento by remember {
        mutableStateOf(false)
    }
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (mostrarDropdownmovimiento) {
                Row(
                    modifier = Modifier
                        .background(color = Color(0xff285B36), shape = RoundedCornerShape(20.dp))
                        .padding(15.dp, 5.dp)
                        .clickable { onIngreso() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        tint = Color.White,
                        painter = painterResource(R.drawable.ic_mas),
                        contentDescription = null,
                        modifier = Modifier.height(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = TextStyle(
                            fontSize = 15.sp
                        ),
                        color = Color.White,
                        text = "Ingreso"
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier
                        .background(color = Color(0xFF740C0C), shape = RoundedCornerShape(20.dp))
                        .padding(15.dp, 5.dp)
                        .clickable { onGasto() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        tint = Color.White,
                        painter = painterResource(R.drawable.ic_menos),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = TextStyle(
                            fontSize = 15.sp
                        ),
                        color = Color.White,
                        text = "Gasto"
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))
            }

            IconButton(
                onClick = { mostrarDropdownmovimiento = !mostrarDropdownmovimiento },
                modifier = modifier
                    .padding(0.dp)
                    .width(35.dp)
                    .background(color = Color(0xff31628D), shape = RoundedCornerShape(50.dp))
            ) {
                Icon(
                    tint = Color.White,
                    painter = painterResource(R.drawable.ic_mas),
                    contentDescription = null
                )
            }
        }
    }
}