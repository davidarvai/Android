package com.example.android_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import repository.recipe.ComponentModel
import repository.recipe.InstructionModel
import repository.recipe.RecipeSharedViewModel
import repository.recipe.RecipesModel
import repository.recipe.SharedPreferencesHelper

class NewRecipeFragment : Fragment() {

    private lateinit var ingredientsContainer: LinearLayout
    private lateinit var instructionsContainer: LinearLayout
    private lateinit var recipeTitle: EditText
    private lateinit var recipeDescription: EditText
    private lateinit var recipePictureUrl: EditText
    private lateinit var recipeVideoUrl: EditText

    private val sharedViewModel: RecipeSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_recipe, container, false)

        recipeTitle = view.findViewById(R.id.et_title)
        recipeDescription = view.findViewById(R.id.et_description)
        recipePictureUrl = view.findViewById(R.id.et_picture_url)
        recipeVideoUrl = view.findViewById(R.id.et_video_url)
        ingredientsContainer = view.findViewById(R.id.ingredients_container)
        instructionsContainer = view.findViewById(R.id.instructions_container)

        view.findViewById<Button>(R.id.btn_add_ingredient).setOnClickListener {
            addIngredientField()
        }

        view.findViewById<Button>(R.id.btn_add_instruction).setOnClickListener {
            addInstructionField()
        }

        view.findViewById<Button>(R.id.btn_save_recipe).setOnClickListener {
            saveRecipe()
        }

        return view
    }

    private fun addIngredientField() {
        val ingredientField = EditText(requireContext())
        ingredientField.hint = "#${ingredientsContainer.childCount + 1} Enter ingredient"
        ingredientField.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        ingredientsContainer.addView(ingredientField)
    }

    private fun addInstructionField() {
        val instructionField = EditText(requireContext())
        instructionField.hint = "#${instructionsContainer.childCount + 1} Enter instruction"
        instructionField.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        instructionsContainer.addView(instructionField)
    }

    private fun saveRecipe() {
        val name = recipeTitle.text.toString()
        val description = recipeDescription.text.toString()
        val thumbnailUrl = recipePictureUrl.text.toString()

        val components = mutableListOf<ComponentModel>()
        for (i in 0 until ingredientsContainer.childCount) {
            val ingredientField = ingredientsContainer.getChildAt(i) as EditText
            components.add(
                ComponentModel(
                    rawText = ingredientField.text.toString(),
                    ingredientName = ingredientField.text.toString(),
                    quantity = "1",
                    unit = ""
                )
            )
        }

        val instructions = mutableListOf<InstructionModel>()
        for (i in 0 until instructionsContainer.childCount) {
            val instructionField = instructionsContainer.getChildAt(i) as EditText
            instructions.add(
                InstructionModel(
                    displayText = instructionField.text.toString(),
                    position = i + 1
                )
            )
        }

        val originalVideoUrl = recipeVideoUrl.text.toString()

        val newRecipe = RecipesModel(
            id = 0,
            name = name,
            description = description,
            thumbnailUrl = thumbnailUrl,
            keywords = "",
            components = components,
            instructions = instructions,
            originalVideoUrl = originalVideoUrl
        )

        lifecycleScope.launch {
            sharedViewModel.addRecipe(newRecipe)
            SharedPreferencesHelper.saveRecipesToPreferences(sharedViewModel.recipesList.value!!, requireContext())  // Elmentjük a receptet
            parentFragmentManager.popBackStack()
        }
    }
}

