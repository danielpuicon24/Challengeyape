package com.my.training.challengeyape.domain.model

import com.my.training.challengeyape.data.database.entities.IngredientesEntity
import com.my.training.challengeyape.data.database.entities.RecetaEntity
import com.my.training.challengeyape.data.model.IngredienteModel
import com.my.training.challengeyape.data.model.RecetaModel


data class Ingrediente (val id : Int, val descripcion : String, val imagen : String, val idReceta : Int? = null)

fun IngredienteModel.toDomain() = Ingrediente(id, descripcion, imagen)
fun IngredientesEntity.toDomain() = Ingrediente(id, descripcion, imagen)