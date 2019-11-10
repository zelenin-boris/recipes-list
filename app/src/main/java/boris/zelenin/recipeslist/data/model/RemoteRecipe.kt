package boris.zelenin.recipeslist.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteRecipe(
    @field:Json(name = "recipe_id") val id: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "image_url") val imageUrl: String?,
    @field:Json(name = "f2f_url") val f2fUrl: String?
)