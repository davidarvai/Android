package com.example.android_project

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.android_project.databinding.FragmentRecipeDetailBinding
import repository.recipe.RecipeDetailViewModel
import repository.recipe.RecipeDetailViewModelFactory
import repository.recipe.RecipeRepository
import repository.recipe.RecipesModel


class RecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: RecipeDetailFragment created.")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeId = arguments?.getInt(RecipesFragment.BUNDLE_EXTRA_SELECTED_RECIPE_ID)
        Log.d(TAG, "Selected recipe id: $recipeId")

        val repository = RecipeRepository()
        repository.initialize(requireContext())

        val factory = RecipeDetailViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[RecipeDetailViewModel::class.java]

        recipeId?.let { viewModel.fetchRecipeDetails(it) }

        viewModel.recipe.observe(viewLifecycleOwner) {
            Log.d(TAG,"Selected recipe's details: $it")
            it?.let { updateViews(it) }
        }
    }


    private fun updateViews(recipeModel: RecipesModel) {
        binding.recipeDetailTitleView.text = recipeModel.name
        binding.recipeDetailDescriptionView.text = recipeModel.description

        Glide.with(this)
            .load(recipeModel.thumbnailUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.recipeDetailImageView)

        // Display ingredients
        binding.ingredientsLayout.removeAllViews()
        recipeModel.components.forEach { component ->
            val ingredientTextView = TextView(context)
            ingredientTextView.text = "${component.quantity} ${component.unit} of ${component.ingredientName}"
            binding.ingredientsLayout.addView(ingredientTextView)
        }

        // Display instructions
        binding.instructionsLayout.removeAllViews()
        recipeModel.instructions.forEach { instruction ->
            val instructionTextView = TextView(context)
            instructionTextView.text = instruction.displayText
            binding.instructionsLayout.addView(instructionTextView)
        }

        // Display nutrition information
        recipeModel.nutrition?.let {
            val nutritionText = "Calories: ${it.calories} kcal\n" +
                    "Protein: ${it.protein}g\n" +
                    "Fat: ${it.fat}g\n" +
                    "Carbs: ${it.carbohydrates}g\n" +
                    "Sugar: ${it.sugar}g\n" +
                    "Fiber: ${it.fiber}g"
            binding.recipeNutritionDetailsView.text = nutritionText
        }
    }

}