package com.example.finanzapp.ui.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finanzapp.models.Categoria
import com.example.finanzapp.models.Movimiento
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@Composable
@Preview
fun GraficoPreview(){
    val listaMovimiento = arrayListOf(
        Movimiento(id = 0, monto = 700.0).apply { categoria = Categoria(1, "Ingreso", 0xff3F8F55, "ic_ingreso") },
        Movimiento(id = 0, monto = 300.0).apply { categoria = Categoria(2, "Diversión", 0xFFAD1457, "ic_divercion") },
        Movimiento(id = 0, monto = 2000.0).apply { categoria = Categoria(2, "Diversión", 0xFFAD1457, "ic_divercion") },
        Movimiento(id = 0, monto = 300.0).apply { categoria = Categoria(3, "Alimento", 0xFF2E7D32, "ic_aliementos") },
        Movimiento(id = 0, monto = 1500.0).apply { categoria = Categoria(4, "Transporte", 0xFFEF6C00, "ic_transporte") }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFE2E2E2)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Grafico(
            movimientos = listaMovimiento
        )
    }
}

@Composable
fun Grafico(
    movimientos: List<Movimiento>,
    modifier: Modifier = Modifier.height(352.dp),
    onCategoriaSelect: (Categoria) -> Unit = {},
) {
    val listaCategorias = mutableListOf<Categoria>()

    movimientos.forEachIndexed { index, item ->
        var categoriaEncontrada = listaCategorias.find { it.id == item.categoria.id }

        if(categoriaEncontrada == null){
            categoriaEncontrada = item.categoria
            categoriaEncontrada.montoGrafico = item.monto.toInt()
            listaCategorias.add(categoriaEncontrada)
        }
        else{
            categoriaEncontrada.montoGrafico += item.monto.toInt()
        }
    }










    val anguloSeparacion = if(listaCategorias.size > 1) 1 else 0
    val totalAngulosSeparacio = listaCategorias.size * anguloSeparacion
    val totalMontos = listaCategorias.sumOf { it.montoGrafico }
    val totalGrados = 360f - totalAngulosSeparacio

    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(20.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier
                .size(350.dp),
            onDraw = {
                val tamano = if(size.width < size.height) size.width else size.height
                val defaultStrokeWidth = tamano * 0.15f//50.dp.toPx()
                var lastAngle = 0f
                listaCategorias.forEachIndexed { ind, item ->
                    val angulosParaUsar = (item.montoGrafico * totalGrados) / totalMontos
                    val centerX = size.width / 2
                    val centerY = size.height / 2
                    val radius = min(centerX, centerY) - defaultStrokeWidth / 2

                    val left = centerX - radius
                    val top = centerY - radius
                    val right = centerX + radius
                    val bottom = centerY + radius

                    drawArc(
                        color = Color(item.color),
                        startAngle = lastAngle,
                        sweepAngle = angulosParaUsar,
                        useCenter = false,
                        topLeft = Offset(left, top),
                        style = Stroke(defaultStrokeWidth, cap = StrokeCap.Butt),
                        size = Size(right - left, bottom - top)
                    )
//                    drawArc(
//                        color = Color(item.color),
//                        startAngle = lastAngle,
//                        sweepAngle = angulosParaUsar,
//                        useCenter = false,
//                        topLeft = Offset((size.width + defaultStrokeWidth) / 4, defaultStrokeWidth / 2),
//                        style = Stroke(defaultStrokeWidth, cap = StrokeCap.Butt),
//                        size = Size(tamano - defaultStrokeWidth,
//                            tamano - defaultStrokeWidth)
//                    )


//                    // Calcular la posición del texto
//                    val textRadius = (size.width - defaultStrokeWidth) / 2
//                    val textAngle = Math.toRadians((lastAngle + angulosParaUsar / 2).toDouble())
//                    val textX = size.width / 2 + textRadius * cos(textAngle).toFloat()
//                    val textY = size.height / 2 + textRadius * sin(textAngle).toFloat()
//
//                    // Dibujar el texto de la categoría (monto)
//                    drawIntoCanvas {
//                        it.nativeCanvas.drawText(
//                            item.montoGrafico.toString(),
//                            textX,
//                            textY,
//                            Paint().apply {
//                                color = Color.Black.toArgb()
//                                textAlign = Paint.Align.CENTER
//                                textSize = 30f // Tamaño de texto personalizable
//                            }
//                        )
//                    }


                    // 5
                    lastAngle += angulosParaUsar + anguloSeparacion
                }
            }
        )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if(listaCategorias.size > 0){
                    val totalIngresos = listaCategorias.filter{it.nombre == "Ingreso"}.sumOf { it.montoGrafico }
                    val totalGastos = listaCategorias.filter { it.nombre != "Ingreso" }.sumOf { it.montoGrafico }
                    val totalCalculo = totalIngresos - totalGastos
                    val colorMonto = if(totalCalculo > 0)  Color(0xff3F8F55) else Color(0xffBA1A1A)
                    Text(text = "Total", fontSize = 20.sp, textAlign = TextAlign.Center)
                    Text(text = "$${totalCalculo}", color = colorMonto, fontSize = 30.sp, textAlign = TextAlign.Center)
                }
                else{
                    Text(
                        text = "No se registraron movimientos",
                        color =  Color(0xff181C20),
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center)

                }
        }
    }
}