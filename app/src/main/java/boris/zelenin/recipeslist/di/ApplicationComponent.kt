package boris.zelenin.recipeslist.di

import android.content.Context
import boris.zelenin.recipeslist.ui.recipelist.RecipeListViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, DatabaseModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    val mainViewModel: RecipeListViewModel
}
