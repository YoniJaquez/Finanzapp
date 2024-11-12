package com.example.finanzapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finanzapp.R
import com.example.finanzapp.models.Categoria

@Composable
fun DropdownPeriodicidad(
    onFiltro: (Categoria) -> Unit
){
    var mostrarDropdownCategorias by remember {
        mutableStateOf(false)
    }

    Row (
        modifier = Modifier
            .shadow(2.dp, shape = RoundedCornerShape(50.dp))
            .background(color = Color.White, shape = RoundedCornerShape(50.dp))
            .padding(15.dp, 5.dp)
            .clickable { mostrarDropdownCategorias = true },
        verticalAlignment = Alignment.CenterVertically
    ){
//        Icon(
//            modifier = Modifier.size(25.dp),
//            tint = Color(0xFF31628D),
//            painter = painterResource(R.drawable.ic_calender),
//            contentDescription = null
//        )

        Spacer(modifier = Modifier.width(5.dp))

        Button(
            onClick = { onFiltro(Categoria(0)) }
        ) {
            Text(
                text = "Totas las categorias",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp
                )

            )

        }





        Spacer(modifier = Modifier.width(5.dp))
//        Icon(
//            modifier = Modifier.size(10.dp),
//            tint = Color.Black,
//            painter = painterResource(R.drawable.ic_chevron_down),
//            contentDescription = null
//        )

        DropdownMenu(
            expanded = mostrarDropdownCategorias,
            onDismissRequest = { mostrarDropdownCategorias = false }
        ) {
            DropdownMenuItem(text = { Text(text = "Anual") }, onClick = { /*TODO*/ })
            DropdownMenuItem(text = { Text(text = "Mensual") }, onClick = { /*TODO*/ })
        }
    }
}