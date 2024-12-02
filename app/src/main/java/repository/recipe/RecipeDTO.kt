package repository.recipe

data class RecipesDTO(
    val recipeId: Int,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val keywords: String,
    val isPublic: Boolean,
    val userEmail: String,
    val originalVideoUrl: String,
    val country: String,
    val numServings: Int,
    val components: List<ComponentDTO>?,
    val instructions: List<InstructionDTO>?,
    val nutrition: NutritionDTO? // Nutrition added to DTO
)

data class NutritionDTO(
    val calories: Int?,
    val protein: Int?,
    val fat: Int?,
    val carbohydrates: Int?,
    val sugar: Int?,
    val fiber: Int?
)

data class RecipesModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val keywords: String,
    val originalVideoUrl: String, // Added this field
    val components: List<ComponentModel> = emptyList(),
    val instructions: List<InstructionModel> = emptyList(),
    val nutrition: NutritionModel? = null // Added nutrition to the model
)

data class ComponentModel(
    val rawText: String,
    val ingredientName: String,
    val quantity: String,
    val unit: String
)

data class InstructionModel(
    val displayText: String,
    val position: Int
)

data class ComponentDTO(
    val rawText: String,
    val extraComment: String,
    val ingredient: IngredientDTO,
    val measurement: MeasurementDTO
)

data class IngredientDTO(
    val name: String
)

data class MeasurementDTO(
    val quantity: String,
    val unit: UnitDTO
)

data class UnitDTO(
    val name: String,
    val displaySingular: String,
    val displayPlural: String,
    val abbreviation: String
)

data class InstructionDTO(
    val instructionID: Int,
    val displayText: String,
    val position: Int
)

data class NutritionModel(
    val calories: Int?,
    val protein: Int?,
    val fat: Int?,
    val carbohydrates: Int?,
    val sugar: Int?,
    val fiber: Int?
)

fun ComponentDTO.toModel(): ComponentModel {
    return ComponentModel(
        rawText = this.rawText,
        ingredientName = this.ingredient.name,
        quantity = this.measurement.quantity,
        unit = this.measurement.unit.name
    )
}

fun InstructionDTO.toModel(): InstructionModel {
    return InstructionModel(
        displayText = this.displayText,
        position = this.position
    )
}

fun NutritionDTO.toModel(): NutritionModel {
    return NutritionModel(
        calories = this.calories,
        protein = this.protein,
        fat = this.fat,
        carbohydrates = this.carbohydrates,
        sugar = this.sugar,
        fiber = this.fiber
    )
}

fun RecipesDTO.toModel(): RecipesModel {
    return RecipesModel(
        id = this.recipeId,
        name = this.name,
        description = this.description,
        thumbnailUrl = this.thumbnailUrl,
        keywords = this.keywords,
        originalVideoUrl = this.originalVideoUrl, // Map originalVideoUrl
        components = this.components?.map { it.toModel() } ?: emptyList(),
        instructions = this.instructions?.map { it.toModel() } ?: emptyList(),
        nutrition = this.nutrition?.toModel() // Map nutrition field
    )
}

fun List<RecipesDTO>.toModelList(): List<RecipesModel> {
    return this.map { it.toModel() }
}
