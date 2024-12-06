package repository.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_project.R

class RecipeAdapter(
    var recipes: MutableList<RecipesModel>,
    private val onRecipeClickListener: (RecipesModel) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_list_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]

        holder.titleTextView.text = recipe.name
        holder.descriptionTextView.text = recipe.description
        holder.keywordsTextView.text = recipe.keywords

        Glide.with(holder.itemView.context)
            .load(recipe.thumbnailUrl)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.recipeImageView)

        holder.itemView.setOnClickListener {
            onRecipeClickListener(recipe)
        }
    }

    override fun getItemCount(): Int = recipes.size

    fun updateData(newRecipes: List<RecipesModel>) {
        recipes.clear()
        recipes.addAll(newRecipes)
        notifyDataSetChanged()
    }

    fun removeItem(recipe: RecipesModel) {
        val position = recipes.indexOf(recipe)
        if (position >= 0) {
            recipes.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImageView: ImageView = itemView.findViewById(R.id.recipeView)
        val titleTextView: TextView = itemView.findViewById(R.id.recipeItemTitleView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.recipeItemDescriptionView)
        val keywordsTextView: TextView = itemView.findViewById(R.id.recipeKeywordsView)
    }
}