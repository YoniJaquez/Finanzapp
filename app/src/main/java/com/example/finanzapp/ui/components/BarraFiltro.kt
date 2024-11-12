package com.example.finanzapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun BarraFiltroPreview(){
    val categorias = listOf(
        Categoria(0, "Todos", 0xFF979797, "logo_splash"),
        Categoria(1, "Ingreso", 0xFF73E600, "ic_varios"),
        Categoria(2, "Hogar", 0xFF283593, "ic_home"),
        Categoria(3, "Alimento", 0xFF2E7D32, "ic_aliementos"),
        Categoria(4, "Transporte", 0xFFEF6C00, "ic_transporte"),
        Categoria(5, "Diversión", 0xFFAD1457, "ic_divercion"),
        Categoria(6, "Ahorro", 0xFFF9A825, "ic_ahorro"),
        Categoria(7, "Educación", 0xFF4527A0, "ic_educacion"),
        Categoria(8, "Servicios", 0xFF00695C, "ic_servicios"),
        Categoria(9, "Varios", 0xFF6A1B9A, "ic_varios")
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red),
        contentAlignment = Alignment.Center
    ){
        BarraFiltro(categorias = categorias, onCategoriaSelect = {})
    }
}
@Composable
fun BarraFiltro(categorias: List<Categoria>, onCategoriaSelect: (Categoria) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier.align(Alignment.CenterVertically),
            tint = Color(0xFF31628D),
            painter = painterResource(R.drawable.ic_filtro),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))

//        Box(
//            modifier = Modifier.align(Alignment.CenterVertically)
//        ) {
//
//            //DropdownPeriodicidad(onFiltro = { categoriaSeleccionada -> onCategoriaSelect(categoriaSeleccionada) })
//        }
        Button(
            onClick = { onCategoriaSelect(Categoria(0)) },
            colors = ButtonDefaults.buttonColors(Color(0xFF31628d)),
            modifier = Modifier.padding(0.dp).height(35.dp)
        ) {
            Text(
                text = "Todas las categorias",
                color = Color.White,
                style = TextStyle(
                    fontSize = 14.sp
                )

            )

        }
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            DropdownCategorias(
                categorias,
                onFiltro = { categoriaSeleccionada -> onCategoriaSelect(categoriaSeleccionada) })
        }

    }
}