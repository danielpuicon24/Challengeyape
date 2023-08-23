package com.my.training.challengeyape.data.network

import com.my.training.challengeyape.data.model.RecetaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecetaService @Inject constructor( private val apiClient : RecetaApiClient) {

    suspend fun getRecetas() : List<RecetaModel>{
        return withContext(Dispatchers.IO){
            val response = apiClient.getAllRecetas()
            response.body() ?: emptyList()
        }
    }
}