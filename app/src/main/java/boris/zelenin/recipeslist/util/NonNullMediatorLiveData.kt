package boris.zelenin.recipeslist.util

import androidx.lifecycle.MediatorLiveData

class NonNullMediatorLiveData<T : Any>(initValue: T) : MediatorLiveData<T>() {

    init {
        value = initValue
    }

    override fun getValue(): T = super.getValue()!!
}