package com.example.android_project

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_project.databinding.FragmentRecipesBinding
import repository.recipe.RecipeListViewModel
import repository.recipe.RecipesListAdapter
import repository.recipe.RecipesModel


class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding
    private lateinit var recipesAdapter: RecipesListAdapter
    private lateinit var viewModel: RecipeListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"Recipe fragment created")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView called in RecipesFragment")


        viewModel = ViewModelProvider(this)[RecipeListViewModel::class.java]
        binding = FragmentRecipesBinding.inflate(inflater,container,false)
        Log.d(TAG, "ViewModel initialized in RecipesFragment")
        context?.let {
            Log.d(TAG, "Context is not null, fetching recipes")
            viewModel.fetchRecipesFromJson(it)
        } ?: Log.d(TAG, "Context is null, cannot fetch recipes")
        initRecycleView()

        viewModel.recipesList.observe(viewLifecycleOwner) { recipes ->
//            Log.d(TAG, "Observing recipesList in RecipesFragment")
//            for (recipe in recipes){
//                Log.d("RecipeData","Recipe Name: ${recipe.name}")
//                Log.d("RecipeData","Recipe description: ${recipe.description}")
//                Log.d("RecipeData","Recipe thumbnail URL: ${recipe.thumbnailUrl}")
//                Log.d("RecipeData","-----------------------------------------")
//            }
//            if (recipes.isEmpty()) {
//                Log.d(TAG, "No recipes found in recipesList")
//            }
            recipesAdapter.setData(recipes)
        }
        return binding.root
    }

    private fun initRecycleView() {
        recipesAdapter = RecipesListAdapter(ArrayList(), requireContext(),
            onItemClickListener = {
                    recipe ->
                navigateToRecipeDetail(recipe)
            })
        binding.recycleView.adapter = recipesAdapter
        binding.recycleView.layoutManager = LinearLayoutManager(context)
    }

    private fun navigateToRecipeDetail(recipe: RecipesModel) {
        findNavController()
            .navigate(
                R.id.action_recipesFragment_to_recipeDetailFragment,
                bundleOf(BUNDLE_EXTRA_SELECTED_RECIPE_ID to recipe.id)
            )
    }

    companion object {
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID = "recipeId"
    }

}