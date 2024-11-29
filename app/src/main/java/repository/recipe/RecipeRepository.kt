package repository.recipe

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class RecipeRepository {

    private var recipesList: List<RecipesModel> = ArrayList()
    private var recipeDetailsList: List<RecipesModel> = ArrayList()

    // Módosított funkció, hogy listát olvasson be a more_recipes.json fájlból
    fun getRecipesFromJson(context: Context): List<RecipesModel> {
        var jsonString: String? = null  // null értékkel kezdjük
        try {
            jsonString = context.assets.open("more_recipes.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.e(TAG, "Error occurred while reading JSON file: $ioException")
        }

        // Ellenőrizzük, hogy a jsonString nem null, mielőtt feldolgoznánk
        if (jsonString != null) {
            val type = object : TypeToken<List<RecipesDTO>>() {}.type
            val recipesDTOList: List<RecipesDTO> = Gson().fromJson(jsonString, type)

            return recipesDTOList.toModelList()
        } else {
            Log.e(TAG, "JSON string is null, cannot process recipes")
            return emptyList()  // Visszatérhetünk egy üres listával, ha nem sikerült olvasni
        }
    }

    // Módosított függvény, hogy egyetlen recept objektumot olvasson be a recipe_details.json fájlból
    fun getRecipeDetailsFromJson(context: Context): RecipesModel? {
        var jsonString: String? = null
        try {
            jsonString = context.assets.open("recipe_details.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.e(TAG, "Error occurred while reading JSON file: $ioException")
        }

        if (jsonString != null) {
            val type = object : TypeToken<RecipesModel>() {}.type
            val recipe: RecipesModel = Gson().fromJson(jsonString, type)

            return recipe
        } else {
            Log.e(TAG, "JSON string is null, cannot process recipe details")
            return null
        }
    }

    // Inicializáló funkció, ami mindkét JSON fájlt beolvassa
    fun initialize(context: Context) {
        recipesList = getRecipesFromJson(context)
        val recipeDetail = getRecipeDetailsFromJson(context)
        recipeDetail?.let {
            recipeDetailsList = listOf(it)  // Egyetlen receptet adunk hozzá a listához
        }
    }

    // Recept keresése az id alapján
    fun getRecipeById(recipeId: Int): RecipesModel? {
        return recipeDetailsList.find { it.id == recipeId }
    }
}