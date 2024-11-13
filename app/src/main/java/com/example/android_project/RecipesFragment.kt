package com.example.android_project

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import repository.recipe.RecipeAdapter
import repository.recipe.RecipeListViewModel



class RecipesFragment : Fragment() {

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: RecipeFragment created.")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProvider(this)[RecipeListViewModel::class.java]

        // RecyclerView inicializálása
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)  // Győződj meg róla, hogy a layoutban létezik a recyclerView ID
        recyclerView.layoutManager = LinearLayoutManager(context)

        context?.let {
            viewModel.fetchRecipesFromJson(it)
        }

        viewModel.recipesList.observe(viewLifecycleOwner) { recipes ->
            // Adatok frissítése az adapterben
            recipeAdapter = RecipeAdapter(recipes)
            recyclerView.adapter = recipeAdapter
        }

        return view
    }
}