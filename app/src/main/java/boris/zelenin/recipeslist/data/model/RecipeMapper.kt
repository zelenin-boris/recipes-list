package boris.zelenin.recipeslist.data.model

fun LocalRecipe.toRecipe() = Recipe(id, title, imageUrl, f2fUrl)
fun List<LocalRecipe>.toRecipes() = map { it.toRecipe() }

fun RemoteRecipe.toLocalRecipe() = LocalRecipe(id, title, imageUrl, f2fUrl)
fun List<RemoteRecipe>.toLocalRecipes() = map { it.toLocalRecipe() }