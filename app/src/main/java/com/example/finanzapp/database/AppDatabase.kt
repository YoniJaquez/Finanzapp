package com.example.finanzapp.database

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finanzapp.R
import com.example.finanzapp.dao.CategoriaDao
import com.example.finanzapp.dao.MovimientoDao
import com.example.finanzapp.models.Categoria
import com.example.finanzapp.models.Movimiento
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

@Database(entities = [Categoria::class, Movimiento::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun categoriaDao(): CategoriaDao
    abstract fun movimientoDao(): MovimientoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database")
                    .build()
                    .also { INSTANCE = it }
            }
        }
        fun initialize(context: Context){
            val db = getDatabase(context)
            val categoriaDao = db.categoriaDao()
            GlobalScope.launch {
                categoriaDao.getAll().collect { items ->
                    if (items.isEmpty()) {
                        categoriaDao.insert(Categoria(0, "Ingreso", 0xff3F8F55, "ic_ingreso"))
                        categoriaDao.insert(Categoria(0, "Hogar", 0xFF283593, "ic_home"))
                        categoriaDao.insert(Categoria(0, "Alimento", 0xFF2E7D32, "ic_aliementos"))
                        categoriaDao.insert(Categoria(0, "Transporte", 0xFFEF6C00, "ic_transporte"))
                        categoriaDao.insert(Categoria(0, "Diversión", 0xFFAD1457, "ic_divercion"))
                        categoriaDao.insert(Categoria(0, "Ahorro", 0xFFF9A825, "ic_ahorro"))
                        categoriaDao.insert(Categoria(0, "Educación", 0xFF4527A0, "ic_educacion"))
                        categoriaDao.insert(Categoria(0, "Servicios", 0xFF00695C, "ic_servicios"))
                        categoriaDao.insert(Categoria(0, "Varios", 0xFF6A1B9A, "ic_varios"))
                    }
                }
            }
//            val items = categoriaDao.getAll()
//            if(items.toList().isEmpty()){
//                categoriaDao.insert(item = Categoria(0, "Ingreso", 0xFF33d300, R.drawable.ic_varios))
//                categoriaDao.insert(item = Categoria(0, "Hogar", 0xFF283593, R.drawable.ic_home))
//                categoriaDao.insert(item = Categoria(0, "Alimento", 0xFF2E7D32, R.drawable.ic_aliementos))
//                categoriaDao.insert(item = Categoria(0, "Transporte", 0xFFEF6C00, R.drawable.ic_transporte))
//                categoriaDao.insert(item = Categoria(0, "Divercion", 0xFFAD1457, R.drawable.ic_divercion))
//                categoriaDao.insert(item = Categoria(0, "Ahorro", 0xFFF9A825, R.drawable.ic_ahorro))
//                categoriaDao.insert(item = Categoria(0, "Educacion", 0xFF4527A0, R.drawable.ic_educacion))
//                categoriaDao.insert(item = Categoria(0, "Servicios", 0xFF00695C, R.drawable.ic_servicios))
//                categoriaDao.insert(item = Categoria(0, "Varios", 0xFF6A1B9A, R.drawable.ic_varios))
//            }
        }
    }


}