package com.example.finanzapp.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date
@Entity
 data class PagosRealizados (

     @PrimaryKey val id: Int,
     @NonNull @ColumnInfo(name = "fecha_pagado")  val fechaPagado : Date = Date(),
     @NonNull @ColumnInfo(name = "nombre")  val name: String = "",
     @NonNull @ColumnInfo(name = "monto")    val monto : Double = 0.0,
     @NonNull @ColumnInfo(name = "categoria") val categoria : Categoria = Categoria(0, "")


 )