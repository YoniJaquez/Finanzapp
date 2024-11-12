package com.example.finanzapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


@Composable
@Preview
fun MensajePreview(){
    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xFFE2E2E2)))
    Mensaje("titulo", "mensaje mensaje mensaje mensaje mensaje mensaje mensaje mensaje mensaje mensaje", onAceptar = {}, onCancelar = {})
}



@Composable
fun Mensaje(
    titulo: String,
    mensaje: String,
    onAceptar: () -> Unit,
    onCancelar: () -> Unit,
    enableCancel: Boolean = false
){
    Dialog(
        onDismissRequest = {
            if(enableCancel){
                onCancelar()
            }
            else{
                onAceptar()
            }
        }
    ){
        Column(modifier = Modifier
//            .fillMaxSize()
            .background(color = Color.White)
            .padding(10.dp)
        ) {

            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(color = Color.White)){
                Text(
                    text = titulo,
                    color = Color.Red,
                    style = TextStyle(
                        fontSize = 15.sp
                    )
                )

            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(color = Color.White)){

                Text(
                    text = mensaje,
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 15.sp
                    )
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                ,
                horizontalArrangement = Arrangement.End

            ) {
                if(enableCancel){
                    Box(modifier =Modifier){
                        Button(onClick = { onCancelar() },
                            colors = ButtonDefaults.buttonColors(Color(0x00F9F3F3)),
                            ) {
                            Text(
                                text = "Cancelar",
                                color = Color.Blue,
                                style = TextStyle(
                                    fontSize = 15.sp
                                )
                            )
                        }
                    }
                }
                Box(modifier =Modifier){
                    Button(onClick = { onAceptar() },
                        colors = ButtonDefaults.buttonColors(Color(0x00F9F3F3))
                    ) {
                        Text(
                            text = "Ok",
                            color = Color.Blue,
                            style = TextStyle(
                                fontSize = 15.sp
                            )
                        )
                    }
                }
            }
        }
    }
}