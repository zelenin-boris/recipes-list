package boris.zelenin.recipeslist.ui.init

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import boris.zelenin.recipeslist.R
import boris.zelenin.recipeslist.ui.recipelist.RecipeListFragment
import kotlinx.android.synthetic.main.fragment_init.view.*

class InitFragment : Fragment() {
    companion object {
        fun newInstance() = InitFragment()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_init, container, false).apply {
                go_button.setOnClickListener {
                    activity!!.supportFragmentManager.commit {
                        replace(R.id.container, RecipeListFragment.newInstance())
                    }
                }
            }
}