package com.example.finanzapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.finanzapp.models.Movimiento
import kotlinx.coroutines.flow.Flow

@Dao
interface MovimientoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Movimiento)

    @Update
    suspend fun update(item :Movimiento)

    @Delete
    suspend fun delete(item: Movimiento)

    @Query("SELECT * FROM movimientos")
    fun getAll(): Flow<List<Movimiento>>

    @Query("SELECT * FROM movimientos WHERE id_categoria = :idCategoria or :idCategoria = 0")
    fun ListAll(idCategoria: Int): Flow<List<Movimiento>>

    @Query("SELECT * FROM movimientos WHERE id_categoria <> :idCategoriaIngreso")
    fun ListGastos(idCategoriaIngreso: Int): Flow<List<Movimiento>>

    @Query("SELECT * FROM movimientos WHERE strftime('%m', fecha) = :mes OR :mes = 0")
    fun ListMes(mes: Int): Flow<List<Movimiento>>


}