import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    var checkList = mutableListOf<Boolean>()
    repeat((1..4).count()) {
        checkList.add(scanner.next().toCharArray()[0].isDigit())
    }
    println(checkList.joinToString(separator = "\\"))
}