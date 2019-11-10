package boris.zelenin.recipeslist.ui.recipelist

import boris.zelenin.recipeslist.data.model.Recipe
import boris.zelenin.recipeslist.util.ErrorEvent
import boris.zelenin.recipeslist.util.NonNullMediatorLiveData

data class MainViewState(
        val recipes: List<Recipe> = emptyList(),
        val isLoading: Boolean = false,
        val error: ErrorEvent? = null
)

fun NonNullMediatorLiveData<MainViewState>.update(
        recipes: List<Recipe> = value.recipes,
        isLoading: Boolean = value.isLoading,
        error: ErrorEvent? = value.error
) {
    value = value.copy(recipes = recipes, isLoading = isLoading, error = error)
}


