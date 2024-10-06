fun String.nameMonogram():String {
    return this.split(" ").map {it[0]}.joinToString(" ")
}
fun List<String>.joinElements(separator:String):String = this.joinToString(separator)

fun List<String>.getLongestElement():String = this.maxByOrNull { it.length } ?: "N/A"


fun main() {

 //Problem 1 -------------------------------------------------------------------------------
 //  val dict: IDictionary = ListDictionary
//    val dict: IDictionary = DictionaryProvider.createDictionary(DictionaryType.HASH_SET)
//    println("Number of words: ${dict.size()}")
//    var word: String?
//    while(true){
//        print("What to find? ")
//        word = readLine()
//        if( word.equals("quit")){
//            break
//        }
//        println("Result: ${word?.let { dict.find(it) }}")
//    }
//------------------------------------------------------------------------------------

//Problem 2  -------------------------------------------------------------------------
//    val name = "John Smith";
//    println(name.nameMonogram());
//
//    val fruits = listOf("apple", "pear", "melon")
//    val result = fruits.joinElements("-")
//    println(result)
//
//
//
//    val fruits2 = listOf("apple", "pear", "strawberry", "melon")
//    val longestFruit = fruits2.getLongestElement()
//    println(longestFruit)
//---------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------

// Problem 3

    //Define a data class Date. The default constructor initializes the year, month and the day
    // with the current date

//    val currentDate = Date()
//    println(currentDate)
//    val specificDate = Date(2001, 4, 18)
//    println(specificDate)
    //---------------------------------------------------------------------------------------------

    // Define an extension function that checks whether the year of the date is a leap year.

//    val currentDate = Date()
//    println("Is the current year a leap year? ${currentDate.isLeapYear()}")
//
//    val leapYearDate = Date(2024, 2, 29)
//    println("Is 2024 a leap year? ${leapYearDate.isLeapYear()}")
//
//    val nonLeapYearDate = Date(2023, 2, 28)
//    println("Is 2023 a leap year? ${nonLeapYearDate.isLeapYear()}")

//--------------------------------------------------------------------------------------------

    // Define an extension function that checks whether the date is a valid one!


//    val validDate = Date(2024, 2, 29)
//    println("Is 2024-02-29 a valid date? ${validDate.isValid()}")
//
//    val invalidDate = Date(2022, 4, 29)
//    println("Is 2022-04-29 a valid date? ${invalidDate.isValid()}")
//
//    val anotherInvalidDate = Date(2023, 13, 1)
//    println("Is 2023-13-01 a valid date? ${anotherInvalidDate.isValid()}")

//--------------------------------------------------------------------------------------------------

    //Main:Generate random dates. Check the validity of the generated date. Valid dates are
    // stored in a list, while invalid ones are printed to the standard output. Repeat the
    // generation process until 10 valid dates are generated.
    //Print the list. Use forEach in order to print each element to a new line.

//    val validDates = mutableListOf<Date>()
//    while (validDates.size < 10) {
//        val randomDate = generateRandomDate()
//        if (randomDate.isValid()) {
//            validDates.add(randomDate)
//        } else {
//            println("Invalid date: $randomDate")
//        }
//    }
//
//
//    println("\nValid dates:")
//    validDates.forEach { println(it) }

//--------------------------------------------------------------------------------------------------

    //Sort the list by defining a natural ordering for the Date class (implement the
    // Comparable<Date> interface!) Print the sorted list.


//    val validDates = mutableListOf<Date>()
//    while (validDates.size < 10) {
//        val randomDate = generateRandomDate()
//        if (randomDate.isValid()) {
//            validDates.add(randomDate)
//        } else {
//            println("Invalid date: $randomDate")
//        }
//    }
//
//    validDates.sort()
//
//    println("\nSorted valid dates:")
//    validDates.forEach { date -> println(date) }

//--------------------------------------------------------------------------------------

    // Reverse the sorted list, then print it.

//    val validDates = mutableListOf<Date>()
//    while (validDates.size < 10) {
//        val randomDate = generateRandomDate()
//        if (randomDate.isValid()) {
//            validDates.add(randomDate)
//        } else {
//            println("Invalid date: $randomDate")
//        }
//    }
//
//    validDates.sort()
//
//    println("\nSorted valid dates:")
//    validDates.forEach { date -> println(date) }
//
//    val reversedDates = validDates.reversed()
//
//    println("\nReversed valid dates:")
//    reversedDates.forEach { date -> println(date) }

//---------------------------------------------------------------------------------------------

    // Sort the list by using a custom ordering (use the Comparator<Date> interface!). Print
    // the sorted list

    val validDates = mutableListOf<Date>()
    while (validDates.size < 10) {
        val randomDate = generateRandomDate()
        if (randomDate.isValid()) {
            validDates.add(randomDate)
        } else {
            println("Invalid date: $randomDate")
        }
    }


    val customComparator = Comparator<Date> { date1, date2 ->
        when {
            date1.month != date2.month -> date1.month - date2.month
            date1.day != date2.day -> date1.day - date2.day
            else -> date1.year - date2.year
        }
    }


    validDates.sortWith(customComparator)


    println("\nSorted valid dates (by month, then day, then year):")
    validDates.forEach { date -> println(date) }



}