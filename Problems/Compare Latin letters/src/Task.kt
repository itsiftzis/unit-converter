import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val firstChar = scanner.next().toCharArray()[0]
    val secondChar = scanner.next().toCharArray()[0]

    println(firstChar.toLowerCase() == secondChar.toLowerCase())
}