package repository.recipe

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_project.R
import com.example.android_project.databinding.RecipeListItemBinding

class RecipesListAdapter(
    private var recipeList: List<RecipesModel>,
    private var context: Context,
    private val onItemClickListener:(RecipesModel) -> Unit
) : RecyclerView.Adapter<RecipesListAdapter.RecipeItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesListAdapter.RecipeItemViewHolder {
        val binding = RecipeListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return RecipeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        val currentRecipe = recipeList[position]
        holder.recipeTitleView.text = currentRecipe.name
        holder.recipeDescriptionView.text = currentRecipe.description
        holder.recipeKeywordView.text = currentRecipe.keywords

        Glide.with(context)
            .load(currentRecipe.thumbnailUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.recipeImageView)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    inner class RecipeItemViewHolder(binding: RecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val recipeTitleView: TextView = binding.recipeItemTitleView
        val recipeDescriptionView: TextView = binding.recipeItemDescriptionView
        val recipeKeywordView: TextView = binding.recipeKeywordsView
        val recipeImageView: ImageView = binding.recipeView

        init {
            binding.root.setOnClickListener {
                val currentPosition = this.adapterPosition
                val currentRecipe = recipeList[currentPosition]
                onItemClickListener(currentRecipe)

            }
        }


    }

    @SuppressLint("NotifyDatsSetChanged", "NotifyDataSetChanged")
    fun setData(newRecipes:List<RecipesModel>) {
        recipeList = newRecipes
        notifyDataSetChanged()
    }

}
