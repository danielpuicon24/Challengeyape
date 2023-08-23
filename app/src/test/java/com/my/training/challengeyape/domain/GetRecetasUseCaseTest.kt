package com.my.training.challengeyape.domain

import com.my.training.challengeyape.data.RecetaRepository
import com.my.training.challengeyape.domain.model.Ingrediente
import com.my.training.challengeyape.domain.model.Receta
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetRecetasUseCaseTest{

    @RelaxedMockK
    private lateinit var repository: RecetaRepository

    lateinit var getRecetasUseCase: GetRecetasUseCase

     @Before
     fun onBefore(){
         MockKAnnotations.init(this)
         getRecetasUseCase = GetRecetasUseCase(repository)
     }

    @Test
    fun `when The Api Doesnt Return Anything Then Get Values From Database`() = runBlocking {
        //Given
        coEvery { repository.getAllRecetasFromApi() } returns emptyList()

        //When
        getRecetasUseCase()

        //Then
        coVerify(exactly = 1) { repository.getAllRecetasFromDatabase() }
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val listIngrediente = listOf(Ingrediente(1, "ingrediente 1", "imagen", 1))
        val list = listOf(
            Receta(1,
                "Receta 1",
                "Esta receta es sobre ...",
                "imagen",
            5,
            -7812.5,
            -15.57,
                listIngrediente
            ))
        coEvery { repository.getAllRecetasFromApi() } returns list

        //When
        val response = getRecetasUseCase()

        //Then
        coVerify (exactly = 1){ repository.clearRecetas() }
        coVerify (exactly = 1){ repository.clearIngrediente() }
        coVerify (exactly = 1){ repository.insertRecetas(any()) }
        coVerify (exactly = 1){ repository.insertIngrediente(any()) }
        coVerify(exactly = 0) { repository.getAllRecetasFromDatabase() }

        assert(list == response)
    }

    @Test
    fun `when user search receta by name or ingredient and there are results in the database`() = runBlocking {
        //Given
        val listIngrediente = listOf(Ingrediente(1, "ingrediente 1", "imagen", 1))
        val recetalist = listOf(
            Receta(1,
                "Receta 1",
                "Esta receta es sobre ...",
                "imagen",
                5,
                -7812.5,
                -15.57,
                listIngrediente
            ))
        val query = "ingrediente"
        coEvery { repository.searchRecetas(query) } returns recetalist

        //When
        val response = getRecetasUseCase.searchRecetaByNameOrIngredients(query)

        //Then
        coVerify (exactly = 1){ repository.searchRecetas(query) }

        assert(recetalist == response)
    }

    @Test
    fun `when the user search receta by name or ingredient and there is no result in the database`() = runBlocking {
        //Given
        val query = "hola"
        coEvery { repository.searchRecetas(query) } returns null

        //When
        val response = getRecetasUseCase.searchRecetaByNameOrIngredients(query)

        //Then
        assert(null == response)
    }
 }