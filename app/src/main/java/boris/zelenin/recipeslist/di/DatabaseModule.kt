package boris.zelenin.recipeslist.di

import android.content.Context
import androidx.room.Room
import boris.zelenin.recipeslist.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "recipies").build()

    @JvmStatic
    @Provides
    fun provideDao(appDatabase: AppDatabase) =
            appDatabase.recipeDao()
}