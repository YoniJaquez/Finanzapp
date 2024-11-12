package com.example.finanzapp.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finanzapp.R
import com.example.finanzapp.models.Categoria
import java.text.DateFormatSymbols
import java.util.Calendar

@Composable
@Preview
fun FiltroNavPreview(){
    Column(modifier = Modifier
        .background(color = Color(0xFFE2E2E2))
        .fillMaxSize()){

        FiltroNav(onFilter = {}, onMesSelected = {})
    }

}
@Composable
fun FiltroNav(
    onFilter: (esIngreso: Boolean) -> Unit,
    onMesSelected: (mes: Int) -> Unit
){
    var mumesSeleccionado by remember{ mutableStateOf("Mes") }
    var mostrarMeses by remember {
        mutableStateOf(false)
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp)
            )
            .padding(5.dp)
    ) {
        Button(onClick = { mostrarMeses = true },
            modifier = Modifier
                .background(
                    color = Color(0xffF7F7F7),
                    shape = RoundedCornerShape(25.dp, 0.dp, 0.dp, 25.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color(0xFF31628d),
                    shape = RoundedCornerShape(25.dp, 0.dp, 0.dp, 25.dp)
                )
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFF7F7F7))
            ) {
            Column(){
                Row {
                    Text(
                        text = mumesSeleccionado,
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 15.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        modifier = Modifier.size(15.dp),
                        tint = Color.Black,
                        painter = painterResource(R.drawable.ic_chevron_down),
                        contentDescription = null
                    )
                }


                if (mostrarMeses) {
                    Box(modifier = Modifier){
                        DropdownMenu(
                            expanded = mostrarMeses,
                            onDismissRequest = { mostrarMeses = false },
                            modifier = Modifier
                                .background(
                                    color = Color(0xFFe7ebef),
                                    shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp)
                                )
                                .clip(RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                                .padding(5.dp, 0.dp)
                        ) {
                            val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
                            val monthNames = DateFormatSymbols().months
                            val monthsToShow = listOf(
                                monthNames[(currentMonth - 3 + 12) % 12],
                                monthNames[(currentMonth - 2 + 12) % 12],
                                monthNames[(currentMonth - 1 + 12) % 12],
                                monthNames[currentMonth]
                            )

                            monthsToShow.forEachIndexed { index, month ->
                                Text(
                                    text = month,
                                    modifier = Modifier
                                        .clickable {
                                            mostrarMeses = false
                                            mumesSeleccionado = month
                                            onMesSelected(index + 1) // Ajusta el índice para que comience desde 1
                                        }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }
        }
        Button(onClick = { onFilter(false) },
            modifier = Modifier
                .background(
                    color = Color(0xffF7F7F7),
                    shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color(0xFF31628d), // Puedes cambiar el color aquí
                    shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp)
                )
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFF7F7F7))
        ) {
            Text(
                text = "Gastos",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 15.sp
                )
            )
        }
        Button(onClick = { onFilter(true) },
            modifier = Modifier
                .background(
                    color = Color(0xffF7F7F7),
                    shape = RoundedCornerShape(0.dp, 25.dp, 25.dp, 0.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color(0xFF31628d),
                    shape = RoundedCornerShape(0.dp, 25.dp, 25.dp, 0.dp)
                )
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFF7F7F7))
        ) {
            Text(
                text = "Ingresos",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 15.sp
                )
            )
        }
    }


}