class ItemController(private val itemService: ItemService) {

    fun quiz(numItems: Int) {
        val questions = itemService.selectRandomItems(numItems)
        var correctAnswers = 0

        questions.forEachIndexed { index, item ->
            println("${index + 1}. ${item.question}")
            item.answers.forEachIndexed { i, answer -> println("${i + 1}. $answer") }
            print("Your answer (1-${item.answers.size}): ")
            val userAnswer = readLine()?.toIntOrNull() ?: -1

            if (userAnswer - 1 == item.correct) {
                correctAnswers++
                println("Correct!\n")
            } else {
                println("Wrong! The correct answer was: ${item.answers[item.correct]}\n")
            }
        }

        println("You got $correctAnswers/${questions.size} correct answers.")
    }


    fun addQuestion() {
        print("Enter the question: ")
        val question = readLine() ?: ""

        val answers = mutableListOf<String>()
        for (i in 1..4) {
            print("Enter answer $i: ")
            val answer = readLine() ?: ""
            answers.add(answer)
        }

        print("Which answer is correct? (1-4): ")
        val correct = (readLine()?.toIntOrNull() ?: 1) - 1

        val newItem = Item(question, answers, correct)
        itemService.saveItem(newItem)

        println("New question saved successfully!")
    }
}