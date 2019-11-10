package boris.zelenin.recipeslist.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import boris.zelenin.recipeslist.R
import boris.zelenin.recipeslist.model.Recipe
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private val adapter = RecipeAdapter { recipe ->
        recipe.f2fUrl?.let {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }

    private val recipes = listOf(
            Recipe(
                    "1",
                    "Title 1",
                    "https://hillfarmoils.com/wp-content/uploads/2018/10/untitled.png-square.png",
                    "http://food2fork.com/view/47024"
            ),
            Recipe(
                    "2",
                    "Title 2",
                    "https://nadialim.com/wp-content/uploads/2018/03/stuffed-baby-kumara-square-555x555.png"
            ),
            Recipe(
                    "3",
                    "Title 3",
                    "https://toppng.com/public/uploads/preview/square-vector-waffle-transparent-background-waffles-clipart-11563053892wucftpn9tz.png"
            ),
            Recipe(
                    "4",
                    "Title 4",
                    "https://nadialim.com/wp-content/uploads/2018/01/Chilli-Con-Carne-square-555x555.png"
            ),
            Recipe(
                    "5",
                    "Title 5",
                    "https://breadboozebacon.com/wp-content/uploads/2017/06/No-Bake-Blueberry-Cheesecake-SQUARE-PNG.png"
            )
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val mainView = inflater.inflate(R.layout.main_fragment, container, false)
        setHasOptionsMenu(true)

        mainView.recipes_list.adapter = adapter
        adapter.submitList(recipes)
        return mainView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort -> {
                adapter.submitList(recipes.sortedBy { it.title }.reversed())
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}