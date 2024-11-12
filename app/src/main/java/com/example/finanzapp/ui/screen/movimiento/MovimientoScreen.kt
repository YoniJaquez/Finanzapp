package com.example.finanzapp.ui.screen.movimiento

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.finanzapp.R
import com.example.finanzapp.database.AppDatabase
import com.example.finanzapp.ui.components.BarraSuperior
import com.example.finanzapp.ui.components.CategoriaItem
import com.example.finanzapp.ui.components.DateTextField
import com.example.finanzapp.ui.components.Mensaje

@Composable
@Preview
fun MovimientoScreenPreview (){
    AppDatabase.initialize(LocalContext.current)
    val database = AppDatabase.getDatabase(LocalContext.current)
    val movimientoDao = database.movimientoDao()
    val categoriaDao = database.categoriaDao()
    val navigation = rememberNavController()
    MovimientoScreen(MovimientoViewModel(movimientoDao, categoriaDao, true), navigation = navigation)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovimientoScreen(
    movimientoViewModel: MovimientoViewModel,
    navigation: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFCFE5FF))
){
    val categorias = movimientoViewModel.categorias
    val nombreMovimiento by movimientoViewModel.nombre.collectAsState()
    val fechaMovimiento by movimientoViewModel.fecha.collectAsState()
    val montoMovimiento by movimientoViewModel.monto.collectAsState()
    val idCategoria by movimientoViewModel.idCategoria.collectAsState()
    val categoriaIngreso by movimientoViewModel.categoriaIngreso.collectAsState()
    val esIngreso by movimientoViewModel.esIngreso.collectAsState()
    val idMovimiento by movimientoViewModel.idMovimiento.collectAsState()
    val tituloAlert by movimientoViewModel.tituloAlert.collectAsState()
    val mensajeAlert by movimientoViewModel.mensajeAlert.collectAsState()

    val titulo = if(esIngreso || idCategoria == categoriaIngreso.id) "Ingreso" else "Gasto"
    val accion = if(idMovimiento == 0) "Agregar" else "Editar"

    Column(modifier = modifier.fillMaxSize()) {

        BarraSuperior(
            onCerrarSesion = {
                navigation.navigate("login"){
                    popUpTo("login") {
                        inclusive = true
                    }
                }
            },
            onConfiguracion = {  }
        )
        Column(modifier = modifier
            .padding(10.dp, 0.dp)
            .fillMaxSize()
        ){

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { navigation.popBackStack() },
                    colors = ButtonDefaults.buttonColors().copy(containerColor = Color.Transparent)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_back) ,
                        contentDescription = null
                    )
                }

                Text(
                    text = "${accion} ${titulo}",
                    modifier = Modifier.weight(1f)    ,
                    style = TextStyle(
                        fontSize = 20.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }



            Spacer(modifier = Modifier.height(10.dp))

            TextField (
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFFFCFCFC)),
                modifier = Modifier
                    .fillMaxWidth(),
                value = nombreMovimiento,
                onValueChange ={
                    if (it.length <= 20){
                        movimientoViewModel.setNombre(it)
                    }
               },
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
                label =  { Text(text = "Concepto del ${titulo}" )},
                trailingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_limpiar), contentDescription = "")
                },
                maxLines = 1,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                colors = TextFieldDefaults
                    .textFieldColors(
                        containerColor = Color(0xFFFCFCFC)
                    ),
                modifier = Modifier
                    .fillMaxWidth(),
                value = montoMovimiento,
                onValueChange = {
                    if(it.toIntOrNull() != null || it.isEmpty()){
                        if (it.length <= 10){
                            movimientoViewModel.setMonto(it)
                        }
                    }
                },
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
                label =  { Text(text = "Monto del ${titulo}" )},
                trailingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_limpiar), contentDescription = "")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(10.dp))
            DateTextField(
                labelText = "Fecha del ${titulo}",
                fechaString = fechaMovimiento,
                onFechaSelected = {
                    movimientoViewModel.setFecha(it)
                }
            )
            Spacer(modifier = Modifier.width(10.dp))

            if(esIngreso || idCategoria == categoriaIngreso.id){
                if(categoriaIngreso.id > 0){
                    movimientoViewModel.setIdCategoria(categoriaIngreso.id)
                }
            }
            else{
                Text(text = "Seleccione Categoria")
                LazyVerticalGrid(columns = GridCells.Fixed(4)){
                    categorias.forEach {
                        if(it.nombre != "Ingreso"){
                            item{
                                CategoriaItem(
                                    categoria = it,
                                    selected = (it.id == idCategoria),
                                    setIdCategoria = { movimientoViewModel.setIdCategoria(it) }
                                )
                            }
                        }
                    }
                }
            }

            Button(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .fillMaxWidth(.7f)
                ,
                onClick = {
                    movimientoViewModel.guardarMovimiento(navigation)
                }
            ) {
                Text(text = "Guardar ${titulo}")
            }
        }

        if(tituloAlert.isNotBlank()){
            Mensaje(
                titulo = tituloAlert,
                mensaje = mensajeAlert,
                onAceptar = { movimientoViewModel.onAlertaDismiss() },
                onCancelar = { movimientoViewModel.onAlertaDismiss() }
            )
        }
    }
}