package com.example.finanzapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finanzapp.models.Categoria
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriaDao {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Categoria)

    @Query("SELECT * FROM categorias")
    fun getAll(): Flow<List<Categoria>>
}