package repository.recipe

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SharedPreferencesHelper {
    private const val PREF_NAME = "RecipeData"
    private const val RECIPES_KEY = "recipes"

    fun saveRecipesToPreferences(recipes: List<RecipesModel>, context: Context) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val gson = Gson()
        val json = gson.toJson(recipes)
        editor.putString(RECIPES_KEY, json)
        editor.apply()
    }

    fun loadRecipesFromPreferences(context: Context): List<RecipesModel> {
        val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPref.getString(RECIPES_KEY, null)
        val type = object : TypeToken<List<RecipesModel>>() {}.type
        return json?.let { gson.fromJson(json, type) } ?: emptyList()
    }
}