package repository.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeSharedViewModel : ViewModel() {
    // Mutable list of recipes
    var recipesList: MutableLiveData<MutableList<RecipesModel>> = MutableLiveData(mutableListOf())

    // Add a new recipe
    fun addRecipe(recipe: RecipesModel) {
        val currentList = recipesList.value ?: mutableListOf()
        currentList.add(recipe)
        recipesList.value = currentList
    }
}