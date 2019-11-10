package boris.zelenin.recipeslist.util

open class Event {
    private var hasNotBeenHandled = true

    fun doIfNotHandled(block: () -> Unit) {
        if (hasNotBeenHandled) {
            hasNotBeenHandled = false
            block()
        }
    }
}

class ErrorEvent : Event()