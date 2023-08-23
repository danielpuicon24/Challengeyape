package com.my.training.challengeyape.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.my.training.challengeyape.domain.model.Ingrediente

@Entity(tableName = "Ingrediente")
data class IngredientesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo("descripcion") val descripcion: String,
    @ColumnInfo("imagen") val imagen: String,
    @ColumnInfo("idReceta") val idReceta: Int
        )

fun Ingrediente.toDatabase() = IngredientesEntity(id = id, descripcion = descripcion, imagen = imagen,idReceta = idReceta!!)