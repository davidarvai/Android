//package com.example.android_project
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.activityViewModels
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import repository.recipe.RecipeSharedViewModel
//import repository.recipe.RecipesListAdapter
//import repository.recipe.RecipesModel
//
//class ProfileFragment : Fragment() {
//
//    private val sharedViewModel: RecipeSharedViewModel by activityViewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_profile, container, false)
//
//        // RecyclerView beállítása
//        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_recipes)
//        val adapter = RecipesListAdapter(
//            recipeList = listOf(), // Kezdeti üres lista
////            context = requireContext(),
//            onItemClickListener = { recipe ->
//                navigateToRecipeDetail(recipe)
//            }
//        )
//
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.adapter = adapter
//
//        // ViewModel megfigyelése az adatok frissítésére
//        sharedViewModel.recipesList.observe(viewLifecycleOwner) { recipes ->
//            adapter.setData(recipes)
//        }
//
//        return view
//    }
//
//    private fun navigateToRecipeDetail(recipe: RecipesModel) {
//        // Bundle létrehozása az adatokkal
//        val bundle = Bundle().apply {
//            putParcelable("recipe", recipe) // RecipesModel Parcelable-nek kell lennie
//        }
//        // Navigálás a RecipeDetailFragment-hez az ID segítségével
//        findNavController().navigate(R.id.recipeDetailFragment, bundle)
//    }
//}