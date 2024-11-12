package com.example.finanzapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finanzapp.models.Categoria
import com.example.finanzapp.models.Movimiento

@Composable
@Preview
fun TotalesPreview(){
    val listaMovimiento = arrayListOf(
        Movimiento(id = 0, monto = 70000.0).apply { categoria = Categoria(0, "Ingreso") },
        Movimiento(id = 0, monto = 300.0).apply { categoria = Categoria(0, "Diversion") },
        Movimiento(id = 0, monto = 20.0).apply { categoria = Categoria(0, "Diversion") },
        Movimiento(id = 0, monto = 300.0).apply { categoria = Categoria(0, "Alimento") },
        Movimiento(id = 0, monto = 300.0).apply { categoria = Categoria(0, "Viaje") }
    )

    Totales(
     movimientos = listaMovimiento
    )

}
@Composable
fun Totales(
    movimientos: List<Movimiento>
){
    var colorMovimiento  = Color.Red
    var total = 0.0

    movimientos.forEach {
        if (it.categoria.nombre  ==  "Ingreso"){
            total = total + it.monto
            colorMovimiento = Color(0xff3F8F55)
        }
        else{
            total = total - it.monto
        }

    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(color = Color(0xFFCFE5FF)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Total",
            color = Color.Black,
            style = TextStyle(
              fontSize = 25.sp
            )
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = "$$total",
            style = TextStyle(
                fontSize = 26.sp,


            ),
            color = colorMovimiento,
            textAlign = TextAlign.Center,

        )

    }
}