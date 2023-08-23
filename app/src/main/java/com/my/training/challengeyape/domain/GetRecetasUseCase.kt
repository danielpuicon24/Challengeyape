package com.my.training.challengeyape.domain

import com.my.training.challengeyape.data.RecetaRepository
import com.my.training.challengeyape.data.database.entities.IngredientesEntity
import com.my.training.challengeyape.data.database.entities.toDatabase
import com.my.training.challengeyape.domain.model.Ingrediente
import com.my.training.challengeyape.domain.model.Receta
import javax.inject.Inject


class GetRecetasUseCase @Inject constructor(
    private val repository: RecetaRepository
){
    suspend operator  fun invoke() : List<Receta>?{
        val recetas =  repository.getAllRecetasFromApi()
        return if(recetas.isNotEmpty()){
            repository.clearRecetas()
            repository.clearIngrediente()
            for (receta in recetas){
                val id = repository.insertRecetas(receta.toDatabase())
                for (ingrediente in receta.ingrediente!!){
                    val ingredientesEntity = IngredientesEntity(
                        descripcion = ingrediente.descripcion,
                        imagen = ingrediente.imagen,
                        idReceta = id.toInt()
                    )
                    repository.insertIngrediente(ingredientesEntity)
                }
            }
            recetas
        }else{
            repository.getAllRecetasFromDatabase()
        }
    }

     suspend fun searchRecetaByNameOrIngredients(query: String): List<Receta>? {
        return repository.searchRecetas(query)
    }

    suspend fun searchRecetaById(id : Int) : Receta{
        return repository.searchRecetaById(id)
    }

    suspend fun searchIngredienteByIdReceta(id : Int) : List<Ingrediente>{
        return repository.searchIngredienteByIdReceta(id)
    }
}