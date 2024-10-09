fun main() {
    val repository = ItemRepository()
    val service = ItemService(repository)
    val controller = ItemController(service)

    while (true) {
        println("Welcome to the Quiz!")
        println("1. Start quiz")
        println("2. Add new question")
        println("3. Exit")
        print("Choose an option: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("How many questions would you like to answer? ")
                val numQuestions = readLine()?.toIntOrNull() ?: 1
                controller.quiz(numQuestions)
            }
            2 -> controller.addQuestion()
            3 -> {
                println("Goodbye!")
                break
            }
            else -> println("Invalid option, try again.")
        }
    }
}