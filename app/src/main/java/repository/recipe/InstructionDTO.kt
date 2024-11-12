package repository.recipe

data class InstructionDTO(
    val instructionID: Int,
    val position: Int,
    val displayText: String
)

data class InstructionModel(
    val id: Int,
    val displayText: String
)

fun InstructionDTO.toModel(): InstructionModel {
    return InstructionModel(
        id = this.instructionID,
        displayText = this.displayText
    )
}

fun List<InstructionDTO>.toModelList(): List<InstructionModel> {
    return this.map { it.toModel() }
}