package com.my.training.challengeyape.data.model

import com.google.gson.annotations.SerializedName

data class IngredienteModel (
    @SerializedName("id") val id: Int,
    @SerializedName("descripcion") val descripcion : String,
    @SerializedName("imagen") val imagen : String,
)