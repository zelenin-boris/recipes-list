package boris.zelenin.recipeslist.ui.recipelist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import boris.zelenin.recipeslist.R
import boris.zelenin.recipeslist.di.component
import boris.zelenin.recipeslist.util.viewModels
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import kotlinx.android.synthetic.main.fragment_recipe_list.view.*

class RecipeListFragment : Fragment() {

    companion object {
        fun newInstance() = RecipeListFragment()
    }

    private val viewModel by viewModels { component.mainViewModel }
    private val adapter = RecipeAdapter { recipe ->
        recipe.f2fUrl?.let {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_recipe_list, container, false).apply {
                setHasOptionsMenu(true)

                recipes_list.adapter = adapter
                swipe_refresh.setOnRefreshListener {
                    viewModel.fetchData()
                }
            }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        onInitialLaunch(savedInstanceState)

        viewModel.state.observe(this) {
            adapter.submitList(it.recipes)
            swipe_refresh.isRefreshing = it.isLoading
            it.error?.doIfNotHandled {
                showError()
            }
        }
    }

    private fun onInitialLaunch(savedInstanceState: Bundle?) {
        savedInstanceState ?: viewModel.fetchData()
    }

    private fun showError() =
            Snackbar.make(root_view, getString(R.string.download_error), Snackbar.LENGTH_LONG).show()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort -> {
                viewModel.sortRecipes()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}