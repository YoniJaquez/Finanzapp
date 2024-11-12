package com.example.finanzapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.finanzapp.models.Categoria

@Composable
fun CategoriaItem(
    categoria: Categoria,
    selected: Boolean,
    setIdCategoria: (Int) -> Unit
){
    val color = if(selected) categoria.color else 0xFF526070
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .size(60.dp)
            .background(
                color = Color(color),
                shape = RoundedCornerShape(50.dp)
            )

            .clickable {
                setIdCategoria(categoria.id)
            }
        ) {
            Image(
                painter = painterResource(categoria.getDrawableResourceId(LocalContext.current)),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
            )
        }
        Text(text = categoria.nombre)
    }
}