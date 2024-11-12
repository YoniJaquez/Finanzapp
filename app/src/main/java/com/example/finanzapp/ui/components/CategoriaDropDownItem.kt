package com.example.finanzapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finanzapp.models.Categoria

@Composable
@Preview
fun CategoriaDropDownItemPreview(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red),
        contentAlignment = Alignment.Center
    ){
        CategoriaDropDownItem(objeto = Categoria(0, "Todos", 0xFF979797, "logo_splash"), onClick = {})
    }
}

@Composable
fun CategoriaDropDownItem(
    objeto : Categoria,
    onClick : (Categoria) -> Unit
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFCFE5FF))
            .padding(8.dp, 4.dp)
            .clickable { onClick(objeto) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(objeto.getDrawableResourceId(LocalContext.current)),
            contentDescription = objeto.nombre,
            modifier = Modifier
                .size(20.dp)
                .background(
                    shape = RoundedCornerShape(50.dp),
                    color = Color(objeto.color)
                )
                .padding(2.dp),
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = objeto.nombre,
            style = TextStyle(
                fontSize = 16.sp
            )
        )
    }
}








































