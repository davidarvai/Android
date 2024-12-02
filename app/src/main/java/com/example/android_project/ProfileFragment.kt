package com.example.android_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Button for navigating back to NewRecipeFragment
        view.findViewById<Button>(R.id.btn_back_to_new_recipe).setOnClickListener {
            // Navigating to NewRecipeFragment
            findNavController().navigate(R.id.action_profileFragment_to_newRecipeFragment)
        }

        return view
    }
}