package com.example.android_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import repository.recipe.RecipeSharedViewModel
import repository.recipe.RecipesModel


class ProfileFragment : Fragment() {

    private lateinit var recipesTextView: TextView
    private val sharedViewModel: RecipeSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        recipesTextView = view.findViewById(R.id.recipes_text_view)

        // Observer beállítása a recipesList változásokra
        sharedViewModel.recipesList.observe(viewLifecycleOwner, Observer { recipes ->
            if (recipes.isEmpty()) {
                recipesTextView.text = "No recipes found"  // Ha üres, ezt jelenítjük meg
            } else {
                displayRecipes(recipes)
            }
        })

        // Button for navigating back to NewRecipeFragment
        view.findViewById<Button>(R.id.btn_back_to_new_recipe).setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_newRecipeFragment)
        }

        return view
    }

    private fun displayRecipes(recipes: List<RecipesModel>) {
        // A receptlistát egy TextView-be jelenítjük meg
        recipesTextView.text = ""
        for (recipe in recipes) {
            recipesTextView.append("Name: ${recipe.name}\n")
            recipesTextView.append("Description: ${recipe.description}\n\n")
        }
    }
}