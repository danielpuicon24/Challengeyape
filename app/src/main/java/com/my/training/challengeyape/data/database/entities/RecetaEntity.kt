package com.my.training.challengeyape.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.my.training.challengeyape.domain.model.Receta

@Entity(tableName = "Receta")
data class RecetaEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo("nombre") val nombre : String,
    @ColumnInfo("descripcion") val descripcion : String,
    @ColumnInfo("imagen") val imagen : String,
    @ColumnInfo("calificacion") val calificacion : Int,
    @ColumnInfo("latitud") val latitud : Double,
    @ColumnInfo("longitud") val longitud : Double,
)

fun Receta.toDatabase() = RecetaEntity(id = id, nombre = nombre, descripcion= descripcion, imagen = imagen,
calificacion = calificacion, latitud = latitud, longitud = longitud)