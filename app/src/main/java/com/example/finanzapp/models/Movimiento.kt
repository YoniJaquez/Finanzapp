package com.example.finanzapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName ="movimientos" )
data class Movimiento(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "nombre")
    val nombre: String = "",

    @ColumnInfo(name = "monto")
    val monto: Double = 0.0,

    @ColumnInfo(name = "fecha")
    val fecha: String = "",

    @ColumnInfo(name = "id_categoria")
    val idCategoria : Int = 0
){
    @Ignore
    var categoria: Categoria = Categoria(0)
}
