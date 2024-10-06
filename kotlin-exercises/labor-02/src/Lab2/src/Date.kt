import java.time.DateTimeException
import java.time.LocalDate
import kotlin.random.Random

data class Date(
    val year: Int = LocalDate.now().year,
    val month: Int = LocalDate.now().monthValue,
    val day: Int = LocalDate.now().dayOfMonth
) : Comparable<Date> {
    override fun compareTo(other: Date): Int {
        return when {
            this.year != other.year -> this.year - other.year
            this.month != other.month -> this.month - other.month
            else -> this.day - other.day
        }
    }
}

fun Date.isLeapYear(): Boolean {
    return (this.year % 4 == 0 && this.year % 100 != 0) || (this.year % 400 == 0)
}

fun Date.isValid(): Boolean {
    return try {
        LocalDate.of(this.year, this.month, this.day)
        true
    } catch (e: DateTimeException) {
        false
    }
}

fun generateRandomDate(): Date {
    val year = Random.nextInt(1900, 2100)
    val month = Random.nextInt(1, 13)
    val day = Random.nextInt(1, 32)
    return Date(year, month, day)
}