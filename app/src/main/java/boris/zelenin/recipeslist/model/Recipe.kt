package boris.zelenin.recipeslist.model

data class Recipe(
        val id: String,
        val title: String,
        val imageUrl: String?,
        val f2fUrl: String? = null
)