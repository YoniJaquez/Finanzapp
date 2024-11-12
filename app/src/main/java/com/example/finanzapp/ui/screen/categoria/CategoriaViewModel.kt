package com.example.finanzapp.ui.screen.categoria

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finanzapp.dao.CategoriaDao
import com.example.finanzapp.models.Categoria
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CategoriaViewModel(private val categoriaDao : CategoriaDao ):ViewModel () {
    val categorias  = mutableStateListOf<Categoria>()
    val nombre = mutableStateOf("")
    val color = mutableStateOf(0L)
    val icono = mutableStateOf("")

    fun setNombre(nombre: String){
        this.nombre.value = nombre
    }
    fun setColor(color : Long){
        this.color.value = color
    }
    fun setIcono(icono : String){
        this.icono.value = icono

    }
    init {
        viewModelScope.launch {
            categoriaDao.getAll().collectLatest {
                categorias.clear()
                categorias.addAll(it)
            }
        }
    }
    fun guardar() {
        val nuevaCategoria = Categoria (0,nombre.value, color.value,icono.value)
        viewModelScope.launch {
            categoriaDao.insert(nuevaCategoria)
        }
         setNombre("")
         setColor(color = 0L)
         setIcono(icono = "")
    }
}