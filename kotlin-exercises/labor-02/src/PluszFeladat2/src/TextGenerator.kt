import kotlin.random.Random

class TextGenerator {
    private val wordMap = mutableMapOf<Pair<String, String>, MutableList<String>>()

    fun learnWords(text: String) {
        val words = text.split(" ").filter { it.isNotBlank() }

        if (words.size < 2) return

        for (i in 0 until words.size - 2) {
            val prefix = Pair(words[i], words[i + 1])
            val postfix = words[i + 2]

            wordMap.computeIfAbsent(prefix) { mutableListOf() }.add(postfix)
        }
    }

    fun generateText(text: String) {
        val words = text.split(" ").filter { it.isNotBlank() }

        if (words.size < 2) return

        var currentPrefix = Pair(words[0], words[1])
        val generatedWords = mutableListOf(currentPrefix.first, currentPrefix.second)


        println("1. ${generatedWords.joinToString(" ")}")

        var step = 2

        while (true) {

            val postfixes = wordMap[currentPrefix]

            if (postfixes.isNullOrEmpty()) break

            val nextWord = postfixes[Random.nextInt(postfixes.size)]
            generatedWords.add(nextWord)

            println("$step. ${generatedWords.joinToString(" ")}")
            step++

            currentPrefix = Pair(currentPrefix.second, nextWord)
        }
    }
}