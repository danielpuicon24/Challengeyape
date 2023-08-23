package com.my.training.challengeyape.data.model

import com.google.gson.annotations.SerializedName
import com.my.training.challengeyape.domain.model.Ingrediente

data class RecetaModel (
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre : String,
    @SerializedName("descripcion") val descripcion : String,
    @SerializedName("imagen") val imagen : String,
    @SerializedName("calificacion") val calificacion : Int,
    @SerializedName("latitud") val latitud : Double,
    @SerializedName("longitud") val longitud : Double,
    @SerializedName("ingredientes") val ingredientes : List<Ingrediente>,
)