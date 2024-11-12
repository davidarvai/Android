package repository.recipe

data class RecipeDTO(
    val recipeID: Int,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val keywords: String,
    val isPublic: Boolean,
    val userEmail: String,
    val originalVideoUrl: String,
    val country: String,
    val numServings: Int,
    val components: List<ComponentDTO> = emptyList(),
    val instructions: List<InstructionDTO> = emptyList()
)


data class RecipeModel(
    val recipeID: Int,
    val name: String,
    val description: String,
    val thumbnailUrl: String
)

fun RecipeDTO.toModel(): RecipeModel {
    return RecipeModel(
        recipeID, name, description, thumbnailUrl
    )
}

fun List<RecipeDTO>.toModelList(): List<RecipeModel> {
    return this.map { it.toModel() }
}