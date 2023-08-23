package com.my.training.challengeyape.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.my.training.challengeyape.domain.GetRecetasUseCase
import com.my.training.challengeyape.domain.model.Ingrediente
import com.my.training.challengeyape.domain.model.Receta
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RecetaViewModelTest{

    @RelaxedMockK
    private lateinit var getRecetasUseCase: GetRecetasUseCase

    private lateinit var recetaViewModel: RecetaViewModel

    @get:Rule
    var rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        recetaViewModel = RecetaViewModel(getRecetasUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all recetas`() = runTest {
        //Given
        val listIngrediente = listOf(Ingrediente(1, "ingrediente 1", "imagen", 1))
        val listIngrediente2 = listOf(Ingrediente(2, "ingrediente 2", "imagen2", 2))
        val recetalist = listOf(
            Receta(1,
                "Receta 1",
                "Esta receta es sobre ...",
                "imagen",
                5,
                -7812.5,
                -15.57,
                listIngrediente
            ),
            Receta(2,
                "Receta 2",
                "Esta receta es sobre ...",
                "imagen",
                5,
                -7812.5,
                -15.57,
                listIngrediente2
            )
        )

        coEvery { getRecetasUseCase() } returns recetalist

        //When
        recetaViewModel.onCreate()

        //Then
        assert(recetaViewModel.recetaModel.value == recetalist)
    }
}