package com.my.training.challengeyape.domain.model

import com.my.training.challengeyape.data.database.entities.RecetaEntity
import com.my.training.challengeyape.data.model.RecetaModel

data class Receta (
    val id : Int,
    val nombre : String,
    val descripcion : String,
    val imagen: String,
    val calificacion: Int,
    val latitud: Double,
    val longitud: Double,
    val ingrediente: List<Ingrediente>? = null
    )

fun RecetaModel.toDomain() = Receta(id, nombre, descripcion, imagen, calificacion, latitud, longitud, ingredientes)
fun RecetaEntity.toDomain() = Receta(id, nombre, descripcion, imagen, calificacion, latitud, longitud)