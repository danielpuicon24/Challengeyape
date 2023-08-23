package com.my.training.challengeyape.data.network

import com.my.training.challengeyape.data.model.RecetaModel
import retrofit2.Response
import retrofit2.http.GET

interface RecetaApiClient {

    @GET("/listarRecetas")
    suspend fun getAllRecetas() : Response<List<RecetaModel>>

}