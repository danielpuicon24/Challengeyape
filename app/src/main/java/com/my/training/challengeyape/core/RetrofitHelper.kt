package com.my.training.challengeyape.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://demo6099891.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}