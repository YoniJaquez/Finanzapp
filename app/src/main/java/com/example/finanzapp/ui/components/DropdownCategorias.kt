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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finanzapp.R
import com.example.finanzapp.models.Categoria

@Composable
@Preview
fun DropdownCategoriasPreview(){
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
        DropdownCategorias(categorias = categorias, onFiltro = {})
    }
}

@Composable
fun DropdownCategorias(categorias:List<Categoria>, onFiltro: (Categoria) -> Unit){
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
        Icon(
            modifier = Modifier.size(25.dp),
            tint = Color(0xFF31628D),
            painter = painterResource(R.drawable.ic_folder),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "Categoria",
            color = Color.Black,
            style = TextStyle(
                fontSize = 14.sp
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Icon(
            modifier = Modifier.size(10.dp),
            tint = Color.Black,
            painter = painterResource(R.drawable.ic_chevron_down),
            contentDescription = null
        )
    }

    DropdownMenu(
        expanded = mostrarDropdownCategorias,
        onDismissRequest = { mostrarDropdownCategorias = false },
        modifier = Modifier
            .padding(0.dp)
            .background(color = Color(0xFFCFE5FF))
    ) {

//        CategoriaDropDownItem(
//            Categoria(0, "Todos", 0xFF979797, "logo_splash"),
//            { categoriaSeleccionada -> onFiltro(categoriaSeleccionada) }
//        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(color = Color(0XffCFE5FF))
        )
        categorias.forEachIndexed {index, it ->
            CategoriaDropDownItem(
                it,
                { categoriaSeleccionada -> onFiltro(categoriaSeleccionada) }
            )
            if(index < categorias.size - 1){
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(color = Color(0XffCFE5FF))
                )
            }
        }
    }
}