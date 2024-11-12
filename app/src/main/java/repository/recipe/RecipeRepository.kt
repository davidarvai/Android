package repository.recipe

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.example.android_project.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class RecipeRepository() {

    fun getRecipeFromJson(context: Context): List<RecipeModel> {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("more_recipes.json").bufferedReader().use {
                it.readText()
            }


        } catch (ioException: IOException) {
            Log.e(TAG, "Error occured while reading JSON file: $ioException")
        }

        val type = object : TypeToken<List<RecipeDTO>>() {}.type
        val recipeDTOList: List<RecipeDTO> = Gson().fromJson(jsonString, type)

        val recipeList = recipeDTOList.toModelList()

        return recipeList
    }


}