package com.my.training.challengeyape.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.training.challengeyape.data.model.RecetaModel
import com.my.training.challengeyape.domain.GetRecetasUseCase
import com.my.training.challengeyape.domain.model.Ingrediente
import com.my.training.challengeyape.domain.model.Receta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecetaViewModel @Inject constructor(
    private val getRecetasUseCase: GetRecetasUseCase
)  : ViewModel() {

    val recetaModel = MutableLiveData<List<Receta>?>()
    val recetaSearchModel = MutableLiveData<List<Receta>?>()
    val ingredientesModel = MutableLiveData<List<Ingrediente>?>()
    val isLoading = MutableLiveData<Boolean>()
    val detalleRecetaModel = MutableLiveData<Receta>()

    fun onCreate(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result  = getRecetasUseCase()
            if(!result.isNullOrEmpty()){
                recetaModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

     suspend fun searchReceta(query : String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val searchResult = getRecetasUseCase.searchRecetaByNameOrIngredients(query)
            if(!searchResult.isNullOrEmpty()){
                recetaSearchModel.postValue(searchResult)
                isLoading.postValue(false)
            }else{
                recetaSearchModel.postValue(emptyList())
                isLoading.postValue(false)
            }
        }
    }

    fun searchRecetaById(id : Int){
        viewModelScope.launch {
            isLoading.postValue(true)
            val searchResult = getRecetasUseCase.searchRecetaById(id)
            val ingredientesResult = getRecetasUseCase.searchIngredienteByIdReceta(id)
            detalleRecetaModel.postValue(searchResult)
            ingredientesModel.postValue(ingredientesResult)
            isLoading.postValue(false)
        }
    }
}