class ItemRepository {
    private val items: MutableList<Item> = mutableListOf(
        Item("What is the correct way to declare a variable in Kotlin?", listOf("val a = 1", "var 1 = a", "int a = 1", "a : val 1"), 0),
        Item("Which function is used to output text to the console?", listOf("console.log()", "print()", "echo()", "System.out.print()"), 1),
        Item("Which keyword is used to define a function in Kotlin?", listOf("func", "define", "function", "fun"), 3),
        Item("What is the capital of France?", listOf("Berlin", "Madrid", "Paris", "Rome"), 2),
        Item("Which language runs in a web browser?", listOf("Java", "C", "Python", "JavaScript"), 3),
        Item("What is the largest planet in our solar system?", listOf("Earth", "Mars", "Jupiter", "Saturn"), 2),
        Item("What does HTML stand for?", listOf("Hyper Text Markup Language", "High Text Markup Language", "Hyperlink and Text Markup Language", "None of these"), 0),
        Item("Which is the smallest prime number?", listOf("0", "1", "2", "3"), 2),
        Item("What year did the Titanic sink?", listOf("1912", "1905", "1898", "1915"), 0),
        Item("What is the speed of light?", listOf("300,000 km/s", "150,000 km/s", "450,000 km/s", "500,000 km/s"), 0),
        Item("Which planet is known as the Red Planet?", listOf("Venus", "Mars", "Earth", "Jupiter"), 1),
        Item("Who wrote 'Romeo and Juliet'?", listOf("Charles Dickens", "Mark Twain", "William Shakespeare", "J.K. Rowling"), 2),
        Item("What is the chemical symbol for water?", listOf("H2O", "O2", "CO2", "HO2"), 0)
    )



    fun randomItems(numItems: Int): List<Item> {
        return items.shuffled().take(numItems)
    }


    fun save(item: Item) {
        items.add(item)
    }


    fun size(): Int {
        return items.size
    }
}