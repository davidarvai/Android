package repository.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeDetailViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _recipe = MutableLiveData<RecipesModel?>()
    val recipe: LiveData<RecipesModel?> get() = _recipe

    fun fetchRecipeDetails(recipeId: Int) {
        val recipeDetails = repository.getRecipeById(recipeId)
        _recipe.postValue(recipeDetails)
    }
}