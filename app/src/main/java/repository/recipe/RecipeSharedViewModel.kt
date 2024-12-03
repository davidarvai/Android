package repository.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeSharedViewModel : ViewModel() {

    private val _recipesList = MutableLiveData<List<RecipesModel>>(emptyList()) // MutableLiveData
    val recipesList: LiveData<List<RecipesModel>> get() = _recipesList

    // A receptek listájának frissítése
    fun setRecipesList(recipes: List<RecipesModel>) {
        _recipesList.value = recipes
    }

    fun addRecipe(recipe: RecipesModel) {
        val currentList = _recipesList.value?.toMutableList() ?: mutableListOf()
        currentList.add(recipe)
        _recipesList.value = currentList
    }
}