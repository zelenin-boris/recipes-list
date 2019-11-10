package boris.zelenin.recipeslist.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import boris.zelenin.recipeslist.data.model.LocalRecipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun recipes(): LiveData<List<LocalRecipe>>

    @Query("SELECT * FROM recipes ORDER BY title")
    fun recipesSorted(): LiveData<List<LocalRecipe>>

    @Query("DELETE FROM recipes")
    suspend fun clear()

    @Insert
    suspend fun insert(recipes: List<LocalRecipe>)
}