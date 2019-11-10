package boris.zelenin.recipeslist.di

import androidx.fragment.app.Fragment

interface DaggerComponentProvider {
    val component: ApplicationComponent
}

val Fragment.component get() = (activity!!.application as DaggerComponentProvider).component