package repository.recipe

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeListViewModel(): ViewModel(){
    val recipeList: MutableLiveData<List<RecipeModel>> = MutableLiveData()

    private val repository: RecipeRepository = RecipeRepository()

    fun fetchRecipesFromJson(context: Context) {
        val recipes = repository.getRecipeFromJson(context)
        recipeList.value = recipes
    }

}