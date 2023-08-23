package com.my.training.challengeyape.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.my.training.challengeyape.data.database.entities.RecetaEntity


@Dao
interface RecetaDao {

    @Query("Select * from Receta")
    suspend fun getAllRecetas(): List<RecetaEntity>

    @Query("Select * from Receta where id = :id")
    suspend fun getRecetaById(id : Int): RecetaEntity

    @Query("SELECT DISTINCT R.* FROM Receta R " +
            "INNER JOIN Ingrediente I ON R.id = I.idReceta " +
            "WHERE R.nombre LIKE '%' || :searchText || '%' OR I.descripcion LIKE '%' || :searchText || '%'")
    suspend fun searchRecetasByNameOrDescription(searchText: String): List<RecetaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( recetas: List<RecetaEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReceta( receta: RecetaEntity) : Long

    @Query("DELETE FROM Receta")
    suspend fun deleteAllRecetas()
}