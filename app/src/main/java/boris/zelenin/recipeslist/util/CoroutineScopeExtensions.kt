package boris.zelenin.recipeslist.util

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

inline fun CoroutineScope.launch(
        noinline block: suspend CoroutineScope.() -> Unit,
        crossinline exceptionHandler: (Throwable) -> Unit
) {
    launch(CoroutineExceptionHandler { _, throwable -> exceptionHandler(throwable) }, block = block)
}
