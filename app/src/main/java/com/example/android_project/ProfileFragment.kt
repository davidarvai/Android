package com.example.android_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import repository.recipe.RecipeSharedViewModel
import repository.recipe.RecipesModel
import repository.recipe.SharedPreferencesHelper


class ProfileFragment : Fragment() {

    private lateinit var recipesTextView: TextView
    private lateinit var deleteButton: ImageButton
    private lateinit var selectedRecipe: RecipesModel // Store the selected recipe for deletion
    private val sharedViewModel: RecipeSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        recipesTextView = view.findViewById(R.id.recipes_text_view)
        deleteButton = view.findViewById(R.id.btn_delete_recipe)

        // Load recipes from SharedPreferences
        val recipes = SharedPreferencesHelper.loadRecipesFromPreferences(requireContext())
        if (recipes.isEmpty()) {
            recipesTextView.text = "No recipes found"
        } else {
            displayRecipes(recipes)
            sharedViewModel.setRecipesList(recipes)
        }

        // Observer for recipe list changes
        sharedViewModel.recipesList.observe(viewLifecycleOwner, Observer { recipes ->
            if (recipes.isEmpty()) {
                recipesTextView.text = "No recipes found"
            } else {
                SharedPreferencesHelper.saveRecipesToPreferences(recipes, requireContext()) // Save updated data
                displayRecipes(recipes) // Update the UI
            }
        })

        // Add new recipe button click
        view.findViewById<ImageButton>(R.id.btn_back_to_new_recipe).setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_newRecipeFragment)
        }

        // Delete recipe button click
        deleteButton.setOnClickListener {
            // Check if a recipe is selected for deletion
            if (::selectedRecipe.isInitialized) {
                val recipes = SharedPreferencesHelper.loadRecipesFromPreferences(requireContext())
                val updatedRecipes = recipes.filter { recipe -> recipe != selectedRecipe } // Remove selected recipe
                SharedPreferencesHelper.saveRecipesToPreferences(updatedRecipes, requireContext()) // Save updated list
                displayRecipes(updatedRecipes) // Update UI
                Toast.makeText(requireContext(), "Recipe deleted successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "No recipe selected for deletion", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun displayRecipes(recipes: List<RecipesModel>) {
        recipesTextView.text = "" // Clear previous text
        for (recipe in recipes) {
            recipesTextView.append("Name: ${recipe.name}\n")
            recipesTextView.append("Description: ${recipe.description}\n\n")
        }

        // Set the first recipe as selected for deletion by default (you can adjust this based on your UI logic)
        if (recipes.isNotEmpty()) {
            selectedRecipe = recipes[0] // For example, automatically select the first recipe
        }
    }

    // You can modify this function later to allow users to select a recipe dynamically
    private fun selectRecipeForDeletion(recipe: RecipesModel) {
        selectedRecipe = recipe
        Toast.makeText(requireContext(), "Selected ${recipe.name} for deletion", Toast.LENGTH_SHORT).show()
    }
}