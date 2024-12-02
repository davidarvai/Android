package repository.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeSharedViewModel : ViewModel() {

    private val _recipesList = MutableLiveData<MutableList<RecipesModel>>()
    val recipesList: LiveData<MutableList<RecipesModel>> get() = _recipesList

    init {
        _recipesList.value = mutableListOf()  // Kezdeti üres lista
    }

    fun addRecipe(recipe: RecipesModel) {
        val currentList = _recipesList.value?.toMutableList() ?: mutableListOf()
        currentList.add(recipe)
        _recipesList.value = currentList  // Frissítjük a listát
    }
}