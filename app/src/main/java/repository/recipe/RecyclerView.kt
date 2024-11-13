package repository.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_project.R

class RecipeAdapter(private val recipes: List<RecipeModel>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeName = itemView.findViewById<TextView>(R.id.recipeName)
        val recipeDescription = itemView.findViewById<TextView>(R.id.recipeDescription)
        val recipeThumbnail = itemView.findViewById<ImageView>(R.id.recipeThumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.recipeName.text = recipe.name
        holder.recipeDescription.text = recipe.description
        Glide.with(holder.itemView.context).load(recipe.thumbnailUrl).into(holder.recipeThumbnail)
    }

    override fun getItemCount() = recipes.size
}