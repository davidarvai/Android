package com.example.android_project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import repository.recipe.RecipeAdapter
import repository.recipe.RecipeListViewModel


class RecipesFragment : Fragment() {

    private val recipeViewModel: RecipeListViewModel by viewModels()
    private lateinit var recipeRecyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeRecyclerView = view.findViewById(R.id.recipeRecyclerView)
        recipeRecyclerView.layoutManager = LinearLayoutManager(context)

        // Observe the recipe list and update UI
        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter = RecipeAdapter(recipes) // Pass the recipes to the adapter
            recipeRecyclerView.adapter = recipeAdapter

            // Logging for debugging (optional)
            for (recipe in recipes) {
                Log.d("RecipeData", "recipe name: ${recipe.name}")
                Log.d("RecipeData", "~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            }
        }

        context?.let {
            recipeViewModel.fetchRecipesFromJson(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }
}