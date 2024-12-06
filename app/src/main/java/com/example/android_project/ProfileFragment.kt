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
    private lateinit var adapter: RecipeAdapter
    private var selectedRecipe: RecipesModel? = null
    private val sharedViewModel: RecipeSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize views
        recipesRecyclerView = view.findViewById(R.id.recipes_recycler_view)
        deleteButton = view.findViewById(R.id.btn_delete_recipe)

        // Initialize adapter
        adapter = RecipeAdapter(mutableListOf()) { recipe ->
            selectedRecipe = recipe
            Toast.makeText(requireContext(), "Selected ${recipe.name} for deletion", Toast.LENGTH_SHORT).show()
        }
        recipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        recipesRecyclerView.adapter = adapter

        // Observe changes in recipes
        sharedViewModel.recipesList.observe(viewLifecycleOwner, Observer { newRecipes ->
            adapter.updateData(newRecipes)
        })

        // Handle delete button click
        deleteButton.setOnClickListener {
            selectedRecipe?.let { recipe ->
                val currentRecipes = adapter.recipes.toMutableList()
                currentRecipes.remove(recipe)
                adapter.updateData(currentRecipes)
                SharedPreferencesHelper.saveRecipesToPreferences(currentRecipes, requireContext())
                Toast.makeText(requireContext(), "Recipe deleted successfully", Toast.LENGTH_SHORT).show()
                selectedRecipe = null
            } ?: Toast.makeText(requireContext(), "No recipe selected for deletion", Toast.LENGTH_SHORT).show()
        }

        // Handle back to new recipe button click
        view.findViewById<ImageButton>(R.id.btn_back_to_new_recipe).setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_newRecipeFragment)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        // Load recipes from SharedPreferences on resume
        val savedRecipes = SharedPreferencesHelper.loadRecipesFromPreferences(requireContext())
        adapter.updateData(savedRecipes)
    }
}