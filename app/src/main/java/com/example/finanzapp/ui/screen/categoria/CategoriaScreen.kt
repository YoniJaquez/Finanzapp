//package com.example.finanzapp.ui.screen.categoria
//
//import androidx.activity.viewModels
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.finanzapp.R
//import com.example.finanzapp.database.AppDatabase
//import com.example.finanzapp.ui.screen.movimiento.MovimientoScreen
//import com.example.finanzapp.ui.screen.movimiento.MovimientoViewModel
//
//@Composable
//@Preview
//fun CategoriaScreenPreview(){
//    AppDatabase.initialize(LocalContext.current)
//    val database = AppDatabase.getDatabase(LocalContext.current)
//    val movimientoDao = database.movimientoDao()
//    val categoriaDao = database.categoriaDao()
//    CategoriaScreen(CategoriaViewModel(categoriaDao,movimientoDao,true))
//
//}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CategoriaScreen(
//    viewModel : CategoriaViewModel,
//    modifier: Modifier = Modifier
//        .fillMaxSize()
//        .background(Color(0xFFCFE5FF))
//){
//    val listadoCategorias = viewModel.categorias
//    val nombreCategoria = viewModel.nombre
//    val categorias = movimientoViewModel.categorias
//    val movimientos = movimientoViewModel.movimientos
//    val nombreMovimiento by movimientoViewModel.nombre.collectAsState()
//    val fechaMovimiento by movimientoViewModel.fecha.collectAsState()
//    val montoMovimiento by movimientoViewModel.monto.collectAsState()
//    val idCategoria by movimientoViewModel.idCategoria.collectAsState()
//    val esIngreso by CategoriaViewModel.esIngreso.collectAsState()
//
//
//    Column(modifier = modifier)  {
//        Box(modifier = Modifier.background(color = Color.White)){
//
//            Row( modifier = Modifier
//                .padding(8.dp)
//                .fillMaxWidth()
//
//            ) {
//                Image(painter = painterResource(R.drawable.logo_banner) ,
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop
//                )
//
//
//            }
//        }
//
//        Spacer(modifier = Modifier.height(10.dp))
//        TextField(
//            colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFFFCFCFC)),
//            modifier = Modifier
//                .fillMaxWidth()
//            ,
//            value = fechaMovimiento,
//            onValueChange ={ movimientoViewModel.setFecha(it) },
//            shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
//            label =  { Text(text ="Fecha del gasto" )},
//            trailingIcon = {
//                Icon(painter = painterResource(R.drawable.ic_calender), contentDescription = "")
//            }
//
//        )
//        Spacer(modifier = Modifier.width(10.dp))
//        TextField(
//            value = nombreCategoria.value,
//            onValueChange ={ viewModel.setNombre(it) }
//        )
//        Button(onClick = { viewModel.guardar() }) {
//            Text(text = "Guardar gasto")
//        }
//
//        LazyColumn(modifier = Modifier.fillMaxWidth()){
//            listadoCategorias.forEach{
//                item{
//                    Row(modifier = Modifier.background(color = Color(it.color))) {
//                        Image(painter = painterResource(it.getDrawableResourceId(LocalContext.current)) ,
//                            contentDescription = null,
//                            contentScale = ContentScale.Crop
//                        )
//                        Text(text = it.nombre)
//                    }
//                }
//            }
//        }
//    }
//
//
//}