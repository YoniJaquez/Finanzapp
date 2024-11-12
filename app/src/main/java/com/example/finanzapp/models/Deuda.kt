package com.example.finanzapp.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity
data class Deuda (
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "nombre") val nombre: String = "",
    @NonNull @ColumnInfo (name = "dia_inicio")  val diaInicio : Int = 0,
    @NonNull @ColumnInfo(name = "dia_fin")    val diaFin : Int = 0,
    @NonNull @ColumnInfo(name ="fecha_culminacion") val fechaCulminacion: Date = Date(),
    @NonNull @ColumnInfo(name = "categoria") val categoria : Categoria = Categoria(0, ""),
    @NonNull @ColumnInfo(name ="periodicidad_dias") val periodicidadDias : Int = 0
)