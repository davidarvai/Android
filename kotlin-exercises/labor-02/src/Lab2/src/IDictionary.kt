interface IDictionary {

    fun add(word: String): Boolean
    fun find(word: String): Boolean
    fun size(): Int

    companion object{
        const val DICTIONARY_NAME = "C:\\Users\\arvai\\Kotlin_laborok\\untitled1\\src\\text.txt"
    }
}