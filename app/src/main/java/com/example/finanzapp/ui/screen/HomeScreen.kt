package com.example.finanzapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.finanzapp.database.AppDatabase
import com.example.finanzapp.models.Categoria
import com.example.finanzapp.ui.components.BarraFiltro
import com.example.finanzapp.ui.components.BarraSuperior
import com.example.finanzapp.ui.components.BotonFlotanteMovimientos
import com.example.finanzapp.ui.components.FiltroNav
import com.example.finanzapp.ui.components.Grafico
import com.example.finanzapp.ui.components.ItemMovimientoHome
import com.example.finanzapp.ui.components.Mensaje
import com.example.finanzapp.ui.components.Totales
import com.example.finanzapp.ui.screen.movimiento.MovimientoViewModel

@Composable
@Preview
fun HomeScreenPreview (){
    AppDatabase.initialize(LocalContext.current)
    val database = AppDatabase.getDatabase(LocalContext.current)
    val navigation = rememberNavController()

    HomeScreen(
        MovimientoViewModel(
            database.movimientoDao(),
            database.categoriaDao(),
            false
        ),
        navigation
    )

}

@Composable
fun HomeScreen(
    movimientoViewModel: MovimientoViewModel,
    navigation: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFCFE5FF))
){
    val categorias = movimientoViewModel.categorias
    val movimientos = movimientoViewModel.movimientos
    val currentMovimiento = movimientoViewModel.currentMovimiento
    val tituloAlert by movimientoViewModel.tituloAlert.collectAsState()
    val mensajeAlert by movimientoViewModel.mensajeAlert.collectAsState()
    val enableAlertCancelar by movimientoViewModel.enableAlertCancelar.collectAsState()
    var dividerHeight by remember { mutableStateOf(350.dp) }

    Column(modifier = modifier)  {
        BarraSuperior(onCerrarSesion = { navigation.popBackStack() }, onConfiguracion = {  })
        FiltroNav(onFilter = {esIngreso ->
            var categoria = Categoria(-1)

            if(esIngreso){
                categoria = categorias.find {c -> c.nombre == "Ingreso"}!!
            }

            movimientoViewModel.filtrarCategoria(categoria)
        },
            onMesSelected = { mes ->
                movimientoViewModel.filtrarMes(mes)
            }
        )




        //Spacer(modifier = Modifier.height(dividerHeight))
        Grafico(movimientos = movimientos, modifier = Modifier.height(dividerHeight))




        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF7f7f7),
                shape = RoundedCornerShape(16.dp, 16.dp)
            )
            .padding(10.dp, 5.dp)
        ) {

            var offsetX by remember { mutableFloatStateOf(0f) }

            HorizontalDivider(
                modifier = Modifier
                    .width(100.dp)
                    .height(5.dp)
                    .background(color = Color(0xFF31628D), shape = RoundedCornerShape(10.dp))
                    .align(Alignment.CenterHorizontally)
                    .draggable(
                        orientation = Orientation.Vertical,
                        state = rememberDraggableState { delta ->
                            offsetX += delta
                            var aux = dividerHeight + (delta.dp * .5f)
                            if (aux > 350.dp) {
                                aux = 350.dp
                            } else if (aux < 200.dp) {
                                aux = 200.dp
                            }
                            dividerHeight = aux
                        }

                    )
            )

            BarraFiltro(categorias, onCategoriaSelect = { categoriaSeleccionada -> movimientoViewModel.filtrarCategoria(categoriaSeleccionada)})
            Box(modifier = Modifier.fillMaxSize(1f)){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ){
                    item{
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    movimientos.forEachIndexed{index, it ->
                        item{
                            ItemMovimientoHome(
                                objeto = it,
                                onEditar = { movimientoViewModel.editarMovimiento(it, navigation) },
                                onEliminar = { movimientoViewModel.confirmaEliminar(it) }
                            )

                            if(index != movimientos.size - 1){
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }
                }
                BotonFlotanteMovimientos(
                    onIngreso = { navigation.navigate("nuevo_ingreso") },
                    onGasto = { navigation.navigate("nuevo_gasto") },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )

                if(tituloAlert.isNotBlank()){
                    Mensaje(
                        titulo = tituloAlert,
                        mensaje = mensajeAlert,
                        onAceptar = { movimientoViewModel.onDeleteConfirm(currentMovimiento.value) },
                        onCancelar = { movimientoViewModel.onAlertaDismiss() },
                        enableCancel = enableAlertCancelar
                    )
                }
            }
        }
    }
}