package repository.recipe

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SharedPreferencesHelper {

    private const val PREF_NAME = "RecipeData"
    private const val RECIPES_KEY = "recipes"

    // Receptek mentése SharedPreferences-be JSON formátumban
    fun saveRecipesToPreferences(recipes: List<RecipesModel>, context: Context) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val gson = Gson()
        val json = gson.toJson(recipes) // Lista átalakítása JSON formátumba
        editor.putString(RECIPES_KEY, json) // JSON mentése
        editor.apply() // Aszinkron módon mentés
    }

    // Receptek betöltése SharedPreferences-ből
    fun loadRecipesFromPreferences(context: Context): List<RecipesModel> {
        val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPref.getString(RECIPES_KEY, null) // Betöltés
        val type = object : TypeToken<List<RecipesModel>>() {}.type
        return if (json != null) {
            gson.fromJson(json, type) // JSON átalakítása Lista típusúvá
        } else {
            emptyList() // Ha nincs adat, üres listát adunk vissza
        }
    }
}