package boris.zelenin.recipeslist.data.api

import boris.zelenin.recipeslist.data.model.RecipeResponse
import retrofit2.http.GET

interface RecipeApi {

    @GET("search")
    suspend fun fetch(): RecipeResponse
}