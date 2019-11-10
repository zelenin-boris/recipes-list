package boris.zelenin.recipeslist.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import boris.zelenin.recipeslist.R
import boris.zelenin.recipeslist.model.Recipe
import kotlinx.android.synthetic.main.recipe_item.view.*

class RecipeAdapter(private val onItemClick: (recipe: Recipe) -> Unit) :
        ListAdapter<Recipe, RecipeAdapter.ViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe) = oldItem == newItem
    }

    class ViewHolder(itemView: View,
                     val onItemClick: (recipe: Recipe) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.image
        private val title = itemView.title

        fun bind(recipe: Recipe) {
            title.text = recipe.title
            image.setImageURI(recipe.imageUrl)

            itemView.setOnClickListener { onItemClick(recipe) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false),
                    onItemClick)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}