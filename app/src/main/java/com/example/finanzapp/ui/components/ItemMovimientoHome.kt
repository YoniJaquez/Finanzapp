package com.example.finanzapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.PopupProperties
import com.example.finanzapp.R
import com.example.finanzapp.models.Categoria
import com.example.finanzapp.models.Movimiento

@Composable
@Preview
fun ItemMovimientoHomePreview(){

    val movimiento = Movimiento(0, "asd", 345.0,"01-01-2021", 1)
    movimiento.categoria = Categoria(2, "Hogar", 0xFF283593, "ic_home")
    ItemMovimientoHome(objeto = movimiento, onEliminar = {}, onEditar = {})
}
@Composable
fun ItemMovimientoHome(
    objeto: Movimiento,
    onEliminar: (Movimiento) -> Unit,
    onEditar: (Movimiento) -> Unit
){
    val color = if(objeto.categoria.nombre == "Ingreso")
        Color(0xFf285b36)
    else
        Color(0xFFDA0D0D)
    val menos = if(objeto.categoria.nombre == "Ingreso") "" else "-"
    var mostrarAcciones by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(15.dp))
            .background(color = Color(0xFFF7F7F7), shape = RoundedCornerShape(15.dp))
            .bottomElevation()
    ) {
        Row(
            modifier = Modifier


//                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(objeto.categoria.getDrawableResourceId(LocalContext.current)),
                contentDescription = objeto.nombre,
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        shape = RoundedCornerShape(50.dp),
                        color = Color(objeto.categoria.color)
                    )
                    .padding(8.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = objeto.nombre,
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = objeto.fecha,
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                )

            }

            Row(){

                Text(
                    text = "${menos}$${objeto.monto.toString().split(".")[0]}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = color
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Image(
                    painter = painterResource(R.drawable.ic_ellipsis_v),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(0.dp)
                        .clickable { mostrarAcciones = true }
                )
            }

        }

        if (mostrarAcciones) {
            Box(modifier = Modifier
                .padding(0.dp)
//                .background(color = Color(0xFFCFE5FF))
                .align(alignment = Alignment.TopEnd)
            ){

                DropdownMenu(
                    expanded = mostrarAcciones,
                    onDismissRequest = { mostrarAcciones = false },
                    modifier = Modifier
                        .background(color = Color(0xFFCFE5FF))
                        .padding(5.dp, 0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .background(color = Color(0xFFCFE5FF))
                            .clickable {
                                onEditar(objeto)
                                mostrarAcciones = false
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_editar),
                            contentDescription = "Editar",
                            modifier = Modifier
                                .size(25.dp)
                                .padding(2.dp)
                                .background(color = Color(0xFFCFE5FF)),
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Editar",
                            style = TextStyle(
                                fontSize = 16.sp
                            ),
                            modifier = Modifier
                                .background(color = Color(0xFFCFE5FF))
                        )
                    }

                    Row(
                        modifier = Modifier
                            .background(color = Color(0xFFCFE5FF))
                            .clickable {
                                mostrarAcciones = false
                                onEliminar(objeto)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_borrar),
                            contentDescription = "Eliminar",
                            modifier = Modifier
                                .size(25.dp)
                                .padding(2.dp)
                                .background(color = Color(0xFFCFE5FF)),
                            tint = Color.Red
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Eliminar",
                            style = TextStyle(
                                fontSize = 16.sp
                            ),
                            modifier = Modifier
                                .background(color = Color(0xFFCFE5FF))
                        )
                    }
//                    Column(
//                        modifier = Modifier
//                            .padding(2.dp)
//                            .background(
//                                color = Color(0xFFFFFFFF),
//                                shape = RoundedCornerShape(16.dp)
//                            )
//                            .padding(10.dp)
//                    ) {
//                    }
                }
            }
        }
    }
}
private fun Modifier.bottomElevation(): Modifier = this.then(Modifier.drawWithContent {
    val paddingPx = 20.dp.toPx()
    clipRect(
        left = 0f,
        top = 0f,
        right = size.width,
        bottom = size.height + paddingPx
    ) {
        this@drawWithContent.drawContent()
    }
})