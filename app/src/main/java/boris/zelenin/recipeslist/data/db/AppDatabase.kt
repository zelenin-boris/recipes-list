package boris.zelenin.recipeslist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import boris.zelenin.recipeslist.data.model.LocalRecipe

@Database(entities = [LocalRecipe::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
}