package com.example.finanzapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finanzapp.R

@Composable
@Preview
fun BarraSuperiorPreview(){
    Column(modifier = Modifier.background(color = Color(0xFFE2E2E2)).fillMaxSize()){

        BarraSuperior(onConfiguracion = {}, onCerrarSesion = {})
    }
}
@Composable
fun BarraSuperior(
    onConfiguracion: () -> Unit,
    onCerrarSesion: () -> Unit
){
    var mostrarAcciones by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.background(color = Color.White)) {

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(R.drawable.logo_banner),
                contentDescription = null,
                tint = Color(0xFF31628d)
            )
            Icon(
                painter = painterResource(R.drawable.ic_round_person),
                contentDescription = null,
                tint = Color(0xFF31628d),
                modifier = Modifier.clickable { mostrarAcciones = true }
            )//configuracion - cerrar sesion
        }

        if (mostrarAcciones) {
            Box(modifier = Modifier.fillMaxWidth()){
                DropdownMenu(
                    expanded = mostrarAcciones,
                    onDismissRequest = { mostrarAcciones = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFFe7ebef), shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                        .clip(RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                        .padding(5.dp, 0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .clickable {
                                onConfiguracion()
                            }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_config),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Configuracion")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row (
                        modifier = Modifier
                            .clickable {
                                onCerrarSesion()
                            }
                    ){
                        Icon(
                            painter = painterResource(R.drawable.ic_back_sesion),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Cerrar Sesión")
                    }
                }
            }
        }
    }
}