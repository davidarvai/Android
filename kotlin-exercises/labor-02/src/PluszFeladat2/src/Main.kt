
fun main() {

    val text = "Now is not the time for sleep, now is the time for party!"

    val generator = TextGenerator()

    generator.learnWords(text)

    generator.generateText(text)
}