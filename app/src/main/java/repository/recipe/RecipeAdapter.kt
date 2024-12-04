package repository.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecipeAdapter(
    private val recipes: List<RecipesModel>,
    private val onRecipeClickListener: (RecipesModel) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.nameTextView.text = recipe.name
        holder.descriptionTextView.text = recipe.description
        holder.itemView.setOnClickListener {
            onRecipeClickListener(recipe)
        }
    }

    override fun getItemCount() = recipes.size

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(android.R.id.text1)
        val descriptionTextView: TextView = itemView.findViewById(android.R.id.text2)
    }
}