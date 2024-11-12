package com.example.finanzapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finanzapp.database.AppDatabase
import com.example.finanzapp.ui.screen.HomeScreen
import com.example.finanzapp.ui.screen.SplashScreen
import com.example.finanzapp.ui.screen.login.LoginScreen
import com.example.finanzapp.ui.screen.movimiento.MovimientoScreen
import com.example.finanzapp.ui.screen.movimiento.MovimientoViewModel

@Composable
fun Navigation(localDatabase: AppDatabase){
    val navigation = rememberNavController()
    NavHost(
        navController = navigation,
        startDestination = "login"
    ){
        composable("splash"){
            SplashScreen(
                navigation
            )
        }
        composable("login"){
            LoginScreen(
                navigation
            )
        }
        composable("home"){
            HomeScreen(
                MovimientoViewModel(
                    localDatabase.movimientoDao(),
                    localDatabase.categoriaDao(),
                    false
                ),
                navigation
            )
        }
        composable("nuevo_ingreso"){
            MovimientoScreen(
                MovimientoViewModel(
                    localDatabase.movimientoDao(),
                    localDatabase.categoriaDao(),
                    true
                ),
                navigation
            )
        }
        composable("nuevo_gasto"){
            MovimientoScreen(
                MovimientoViewModel(
                    localDatabase.movimientoDao(),
                    localDatabase.categoriaDao(),
                    false
                ),
                navigation
            )
        }
        composable("editar_movimiento"
            .plus("/{id_movimiento}")
            .plus("/{id_categoria}")
            .plus("/{nombre}")
            .plus("/{monto}")
            .plus("/{fecha}")
        ){
            val vm = MovimientoViewModel(
                    localDatabase.movimientoDao(),
                    localDatabase.categoriaDao(),
                    false
            )
            vm.setIdMovimiento(it.arguments!!.getString("id_movimiento")!!.toInt())
            vm.setIdCategoria(it.arguments!!.getString("id_categoria")!!.toInt())
            vm.setNombre(it.arguments!!.getString("nombre")!!)
            vm.setMonto(it.arguments!!.getString("monto")!!.split(".")[0])
            vm.setFecha(it.arguments!!.getString("fecha")!!)
            MovimientoScreen(
                vm,
                navigation
            )
        }
    }
}