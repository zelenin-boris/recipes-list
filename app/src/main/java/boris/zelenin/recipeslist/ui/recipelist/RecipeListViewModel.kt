package boris.zelenin.recipeslist.ui.recipelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import boris.zelenin.recipeslist.data.api.RecipeApi
import boris.zelenin.recipeslist.data.db.RecipeDao
import boris.zelenin.recipeslist.data.model.toLocalRecipes
import boris.zelenin.recipeslist.data.model.toRecipes
import boris.zelenin.recipeslist.util.ErrorEvent
import boris.zelenin.recipeslist.util.NonNullMediatorLiveData
import boris.zelenin.recipeslist.util.launch
import timber.log.Timber
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(
        private val recipeDao: RecipeDao,
        private val recipeApi: RecipeApi
) : ViewModel() {
    private val _state = NonNullMediatorLiveData(MainViewState()).apply {
        addSource(recipeDao.recipes()) { update(recipes = it.toRecipes()) }
    }
    val state: LiveData<MainViewState>
        get() = _state

    fun fetchData() {
        viewModelScope.launch(
                block = {
                    _state.update(isLoading = true)
                    recipeDao.clear()
                    recipeDao.insert(recipeApi.fetch().recipes.toLocalRecipes())
                    _state.update(isLoading = false)
                },
                exceptionHandler = ::handleException
        )
    }

    fun sortRecipes() {
        with(_state) {
            removeSource(recipeDao.recipes())
            addSource(recipeDao.recipesSorted()) { update(recipes = it.toRecipes()) }
        }
    }

    private fun handleException(throwable: Throwable) {
        Timber.e(throwable)
        _state.update(isLoading = false, error = ErrorEvent())
    }
}