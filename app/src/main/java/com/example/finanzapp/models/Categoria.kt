package com.example.finanzapp.models

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.finanzapp.R

@Entity(tableName = "categorias")
data class Categoria (
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "nombre")
    val nombre: String = "",

    @ColumnInfo(name = "color")
    val color : Long = 0xffffffff,

    @ColumnInfo(name = "icono")
    val icono : String = ""
){
    @Ignore
    var montoGrafico: Int = 0
    fun getDrawableResourceId(context: Context): Int{
        if(icono.isEmpty()){
            return R.drawable.ic_limpiar
        }
        else{
            return context.resources.getIdentifier(icono, "drawable", context.packageName)
        }
    }
}