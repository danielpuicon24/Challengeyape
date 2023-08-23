package com.my.training.challengeyape.data

import com.my.training.challengeyape.data.database.dao.IngredienteDao
import com.my.training.challengeyape.data.database.dao.RecetaDao
import com.my.training.challengeyape.data.database.entities.IngredientesEntity
import com.my.training.challengeyape.data.database.entities.RecetaEntity
import com.my.training.challengeyape.data.network.RecetaService
import com.my.training.challengeyape.domain.model.Ingrediente
import com.my.training.challengeyape.domain.model.Receta
import com.my.training.challengeyape.domain.model.toDomain
import javax.inject.Inject

class RecetaRepository @Inject constructor(
    private val api: RecetaService,
    private val recetaDao: RecetaDao,
    private val ingredienteDao: IngredienteDao

) {


    suspend fun getAllRecetasFromApi():List<Receta>{
        return api.getRecetas().map { it.toDomain() }
    }

    suspend fun getAllRecetasFromDatabase():List<Receta>{
        return recetaDao.getAllRecetas().map { it.toDomain() }
    }

    suspend fun insertRecetas(recetaEntity: RecetaEntity) : Long {
        return recetaDao.insertReceta(recetaEntity)
    }

    suspend fun clearRecetas(){
        recetaDao.deleteAllRecetas()
    }

    suspend fun insertIngrediente(ingredientesEntity: IngredientesEntity){
        ingredienteDao.insertIngrediente(ingredientesEntity)
    }

    suspend fun clearIngrediente(){
        ingredienteDao.deleteAllIngredientes()
    }

    suspend fun searchRecetaById(id : Int) : Receta{
        return recetaDao.getRecetaById(id).toDomain()
    }

    suspend fun searchIngredienteByIdReceta(id : Int) : List<Ingrediente>{
        return ingredienteDao.getAllIngredientesByIdReceta(id).map { it.toDomain() }
    }

    suspend fun searchRecetas(query: String): List<Receta>? {
        return recetaDao.searchRecetasByNameOrDescription(query).map { it.toDomain() }
    }
//     suspend fun searchRecetas(query: String): List<RecetaModel> {
//        // Implementa la lógica para realizar la búsqueda en tu fuente de datos (por ejemplo, una API o una base de datos)
//        val filterRecetas : List<RecetaModel> = api.getRecetas().filter { receta ->
//            receta.nombre.contains(query, ignoreCase = true)
//                    || receta.detalle.ingredientes.any{
//                    ingrediente -> ingrediente.descripcion.contains(query, ignoreCase = true)
//            }
//        }
//        return filterRecetas
//    }
}