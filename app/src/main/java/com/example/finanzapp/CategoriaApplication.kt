package com.example.finanzapp

import android.app.Application
import com.example.finanzapp.database.AppDatabase

class CategoriaApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}