package com.my.training.challengeyape.di

import android.content.Context
import androidx.room.Insert
import androidx.room.Room
import com.my.training.challengeyape.data.database.RecetaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val RECETA_DATABASE = "receta_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RecetaDatabase::class.java, RECETA_DATABASE).build()

    @Singleton
    @Provides
    fun provideRecetaDao(db : RecetaDatabase) = db.getRecetaDao()


    @Singleton
    @Provides
    fun provideIngredienteRecetaDao(db : RecetaDatabase) = db.getIngredienteRecetaDao()
}