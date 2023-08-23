package com.my.training.challengeyape.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.my.training.challengeyape.data.database.entities.IngredientesEntity
import com.my.training.challengeyape.data.database.entities.RecetaEntity
import com.my.training.challengeyape.domain.model.Ingrediente

@Dao
interface IngredienteDao {

    @Query("Select * from Ingrediente where idReceta = :idReceta")
    suspend fun getAllIngredientesByIdReceta(idReceta : Int): List<IngredientesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( ingredientes: List<IngredientesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngrediente( ingrediente: IngredientesEntity)

    @Query("DELETE FROM Ingrediente")
    suspend fun deleteAllIngredientes()
}