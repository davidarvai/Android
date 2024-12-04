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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import repository.recipe.RecipeAdapter
import repository.recipe.RecipeSharedViewModel
import repository.recipe.RecipesModel
import repository.recipe.SharedPreferencesHelper


class ProfileFragment : Fragment() {

    private lateinit var recipesRecyclerView: RecyclerView
    private lateinit var deleteButton: ImageButton
    private var selectedRecipe: RecipesModel? = null
    private val sharedViewModel: RecipeSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        recipesRecyclerView = view.findViewById(R.id.recipes_recycler_view)
        deleteButton = view.findViewById(R.id.btn_delete_recipe)

        // Load recipes from SharedPreferences
        val recipes = SharedPreferencesHelper.loadRecipesFromPreferences(requireContext())
        if (recipes.isEmpty()) {
            Toast.makeText(requireContext(), "No recipes found", Toast.LENGTH_SHORT).show()
        } else {
            setupRecyclerView(recipes)
        }

        // Observer for recipe list changes
        sharedViewModel.recipesList.observe(viewLifecycleOwner, Observer { recipes ->
            if (recipes.isEmpty()) {
                Toast.makeText(requireContext(), "No recipes found", Toast.LENGTH_SHORT).show()
            } else {
                SharedPreferencesHelper.saveRecipesToPreferences(recipes, requireContext())
                setupRecyclerView(recipes) // Update RecyclerView with new data
            }
        })

        // Add new recipe button click
        view.findViewById<ImageButton>(R.id.btn_back_to_new_recipe).setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_newRecipeFragment)
        }

        // Delete button click
        deleteButton.setOnClickListener {
            // Check if a recipe is selected
            selectedRecipe?.let {
                val recipes = SharedPreferencesHelper.loadRecipesFromPreferences(requireContext())
                val updatedRecipes = recipes.filter { recipe -> recipe != selectedRecipe }
                SharedPreferencesHelper.saveRecipesToPreferences(updatedRecipes, requireContext())
                setupRecyclerView(updatedRecipes) // Update UI
                Toast.makeText(requireContext(), "Recipe deleted successfully", Toast.LENGTH_SHORT).show()
            } ?: run {
                Toast.makeText(requireContext(), "No recipe selected for deletion", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun setupRecyclerView(recipes: List<RecipesModel>) {
        // Set up the RecyclerView with a LinearLayoutManager and the RecipeAdapter
        recipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RecipeAdapter(recipes) { recipe ->
            // When a recipe is clicked, set it as the selected one
            selectedRecipe = recipe
            Toast.makeText(requireContext(), "Selected ${recipe.name} for deletion", Toast.LENGTH_SHORT).show()
        }
        recipesRecyclerView.adapter = adapter
    }
}