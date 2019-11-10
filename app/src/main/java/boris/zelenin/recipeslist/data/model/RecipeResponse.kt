package boris.zelenin.recipeslist.data.model

import com.squareup.moshi.Json

data class RecipeResponse(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "recipes") val recipes: List<RemoteRecipe>
)