package com.example.finanzapp.ui.screen.movimiento

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.finanzapp.dao.CategoriaDao
import com.example.finanzapp.dao.MovimientoDao
import com.example.finanzapp.models.Categoria
import com.example.finanzapp.models.Movimiento
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class MovimientoViewModel (
    private val movimientoDao : MovimientoDao,
    private val categoriaDao : CategoriaDao,
    private val registraIngreso : Boolean
): ViewModel() {
    val categorias  = mutableStateListOf<Categoria>()
    val movimientos  = mutableStateListOf<Movimiento>()

    private val _categoriaIngreso = MutableStateFlow(Categoria(0))
    val categoriaIngreso: StateFlow<Categoria> = _categoriaIngreso.asStateFlow()

    private val _currentMovimiento = MutableStateFlow(Movimiento(0))
    val currentMovimiento: StateFlow<Movimiento> = _currentMovimiento.asStateFlow()

    private val _idMovimiento = MutableStateFlow(0)
    val idMovimiento: StateFlow<Int> = _idMovimiento.asStateFlow()

    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre.asStateFlow()

    private val _monto = MutableStateFlow("")
    val monto: StateFlow<String> = _monto.asStateFlow()

    private val _fecha = MutableStateFlow(SimpleDateFormat("dd-MM-yyyy").format(Date()))
    val fecha: StateFlow<String> = _fecha.asStateFlow()

    private val _idCategoria = MutableStateFlow(0)
    val idCategoria: StateFlow<Int> = _idCategoria.asStateFlow()

    private val _esIngreso = MutableStateFlow(registraIngreso)
    val esIngreso: StateFlow<Boolean> = _esIngreso.asStateFlow()

    private val _tituloAlert = MutableStateFlow("")
    val tituloAlert: StateFlow<String> = _tituloAlert.asStateFlow()

    private val _mensajeAlert = MutableStateFlow("")
    val mensajeAlert: StateFlow<String> = _mensajeAlert.asStateFlow()

    private val _enableAlertCancelar = MutableStateFlow(false)
    val enableAlertCancelar: StateFlow<Boolean> = _enableAlertCancelar.asStateFlow()

    fun setIdMovimiento(idMovimiento: Int){
        _idMovimiento.value = idMovimiento
    }

    fun setNombre(nombre : String){
        _nombre.value = nombre
    }

    fun setMonto(monto : String){
        _monto.value = monto.split(".")[0]
    }
    fun setFecha(fecha  : String){
        _fecha.value = fecha
    }
    fun setIdCategoria(idCategoria : Int){
        _idCategoria.value = idCategoria
    }

    init {
        cargarCategorias()
    }
    private fun cargarCategorias(){
        viewModelScope.launch {
            categorias.clear()
            categoriaDao.getAll().collectLatest {
                categorias.addAll(it)
                _categoriaIngreso.value = categorias.find{ it.nombre == "Ingreso" }!!
                cargarMovimientos()
            }
        }
    }
    private fun cargarMovimientos(){
        viewModelScope.launch {
            movimientoDao.getAll().collectLatest {movimientosLocal ->
                movimientos.clear()
                movimientosLocal.forEach {
                    val categoriaActual = categorias.find { c -> c.id == it.idCategoria }
                    if(categoriaActual == null){
                        it.categoria = Categoria(0, "Sin categoria", 0xFFFF0000, "ic_limpiar")
                    }
                    else{
                        it.categoria = categoriaActual
                    }
                }
                movimientos.addAll(movimientosLocal)
            }
        }
    }
    fun guardarMovimiento(navigation: NavHostController) {
        if(validarCamposMovimiento()){
            val objetoMovimiento = Movimiento(idMovimiento.value,nombre.value,monto.value.toDouble() , fecha.value , idCategoria.value )
            viewModelScope.launch {
                if(objetoMovimiento.id == 0){
                    movimientoDao.insert(objetoMovimiento)
                }
                else{
                    movimientoDao.update(objetoMovimiento)
                }
                cargarMovimientos()

                navigation.popBackStack()
            }
            setIdMovimiento(0)
            setNombre("")
            setMonto("0")
            setFecha("")
            setIdCategoria(0)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun validarCamposMovimiento(): Boolean{
        val titulo = if(esIngreso.value) "ingreso" else "gasto"

        if(nombre.value.length < 3 || nombre.value.length >20){
            _tituloAlert.value = "Concepto incorrecto"
            _mensajeAlert.value = "Ingrese un concepto de ${titulo} válido"
            return false
        }

        if(monto.value.toIntOrNull() == null){
            _tituloAlert.value = "Monto incorrecto"
            _mensajeAlert.value = "Ingrese un Monto de ${titulo} válido"
            return false
        }

        val fechaFormateada = SimpleDateFormat("dd-MM-yyyy").parse(fecha.value.replace("/", "-"))!!
        val fechaHoy = Date()
        if(fechaFormateada.after(fechaHoy)){
            _tituloAlert.value = "Concepto incorrecto"
            _mensajeAlert.value = "Ingrese una fecha de ${titulo} válido"
            return false
        }

        if(_idCategoria.value == 0){
            _tituloAlert.value = "Categoría incorrecto"
            _mensajeAlert.value = "Ingrese una categoría de ${titulo} válido"
            return false
        }

        return true
    }

    fun onAlertaDismiss(){
        _tituloAlert.value = ""
        _mensajeAlert.value = ""
        _enableAlertCancelar.value = false
    }

    fun onDeleteConfirm(objeto: Movimiento){
        eliminarMovimiento(objeto)
        onAlertaDismiss()
    }
    fun confirmaEliminar(objeto: Movimiento){
        val titulo = if (objeto.categoria.nombre == "Ingreso") "Ingreso" else "Gasto"
        _tituloAlert.value = "Eliminar ${titulo}"
        _mensajeAlert.value = "Seguro que quiere eliminar el ${titulo.lowercase()} ${objeto.nombre} (${objeto.categoria.nombre})?\nNo podrá recuperar la información."
        _currentMovimiento.value = objeto
        _enableAlertCancelar.value = true
    }

    fun filtrarCategoria(objeto: Categoria){
        viewModelScope.launch {
            if(objeto.id != -1){
                movimientoDao.ListAll(objeto.id).collectLatest{movimientosFiltrados ->
                    actualizarListaMovimientos(movimientosFiltrados)
                }
            }
            else{
                val categoriaIngreso = categorias.find { c -> c.nombre == "Ingreso" }!!
                movimientoDao.ListGastos(categoriaIngreso.id).collectLatest{movimientosFiltrados ->
                    actualizarListaMovimientos(movimientosFiltrados)
                }
            }
        }
    }
    fun filtrarMes(mes: Int){
        viewModelScope.launch {
            movimientoDao.ListAll(0).collectLatest{movimientosFiltrados ->
                val mesFiltrado = movimientosFiltrados.filter { m ->
                    val fechaAuxiliar = m.fecha.replace("/", "-").replace("0", "").split("-")
                    if(fechaAuxiliar.size > 1){
                        fechaAuxiliar[1] == mes.toString()
                    }
                    else{
                        false
                    }
                }
                actualizarListaMovimientos(mesFiltrado)
            }
        }
    }

    fun actualizarListaMovimientos(movimientosFiltrados: List<Movimiento>){
        movimientos.clear()
        movimientosFiltrados.forEach {
            val categoriaActual = categorias.find { c -> c.id == it.idCategoria }
            if(categoriaActual == null){
                it.categoria = Categoria(0, "Sin categoria", 0xFFFF0000, "ic_limpiar")
            }
            else{
                it.categoria = categoriaActual
            }
        }
        movimientos.addAll(movimientosFiltrados)
    }

    fun editarMovimiento(objeto: Movimiento, navigation: NavHostController) {
        navigation.navigate("editar_movimiento"
            .plus("/${objeto.id}")
            .plus("/${objeto.idCategoria}")
            .plus("/${objeto.nombre}")
            .plus("/${objeto.monto}")
            .plus("/${objeto.fecha.replace("/", "-")}")
        )
    }

    fun eliminarMovimiento(objeto: Movimiento) {
        viewModelScope.launch {
            movimientoDao.delete(objeto)
            cargarMovimientos()
        }
    }
}