import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val firstChar = scanner.next().toCharArray()[0]
    val secondChar = scanner.next().toCharArray()[0]
    val thirdChar = scanner.next().toCharArray()[0]

    if (isRightAfter(firstChar, secondChar) && isRightAfter(secondChar, thirdChar)) {
        println(true)
    } else {
        println(false)
    }
}

fun isRightAfter(firstChar: Char, secondChar: Char): Boolean {
    return secondChar - firstChar == 1
}
