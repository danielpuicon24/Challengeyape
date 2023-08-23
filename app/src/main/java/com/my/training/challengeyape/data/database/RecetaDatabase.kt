package com.my.training.challengeyape.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.my.training.challengeyape.data.database.dao.IngredienteDao
import com.my.training.challengeyape.data.database.dao.RecetaDao
import com.my.training.challengeyape.data.database.entities.IngredientesEntity
import com.my.training.challengeyape.data.database.entities.RecetaEntity

@Database(entities = [RecetaEntity::class, IngredientesEntity::class], version = 1)
abstract class RecetaDatabase : RoomDatabase() {

    abstract fun getRecetaDao():RecetaDao

    abstract fun getIngredienteRecetaDao():IngredienteDao
}