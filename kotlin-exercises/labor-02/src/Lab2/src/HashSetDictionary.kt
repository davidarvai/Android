import IDictionary.Companion.DICTIONARY_NAME
import java.io.File
import java.util.HashSet

object HashSetDictionary : IDictionary{
    private var words =  HashSet<String>()

    init {
        File(DICTIONARY_NAME).forEachLine { add(it) }
        println("File reading finished!")
    }

    override fun add(word: String) = words.add(word)

    override fun find(word: String) = words.find { it == word } != null

    override fun size () = words.size
}