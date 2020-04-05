package converter

import java.util.*

private const val METERS_PER_KILOMETER = 1000
private const val METERS_PER_CENTIMETER = 0.01
private const val METERS_PER_MILLIMETER = 0.001
private const val METERS_PER_MILE = 1609.35
private const val METERS_PER_YARD = 0.9144
private const val METERS_PER_FOOT = 0.3048
private const val METERS_PER_INCH = 0.0254

private val meterUnits = listOf("meter", "meters", "m")
private val kilometersUnits = listOf("kilometer", "kilometers", "km")
private val centimeterUnits = listOf("centimeter", "centimeters", "cm")
private val millimeterUnits = listOf("millimeter", "millimeters", "mm")
private val mileUnits = listOf("mile", "miles", "mi")
private val feetUnits = listOf("foot", "feet", "ft")
private val inchesUnits = listOf("inch", "inches", "in")
private val yardUnits = listOf("yard", "yards", "yd")

fun main() {
    val scanner = Scanner(System.`in`)
    println("Enter a number and a measure of length:")
    val inputValue = scanner.nextDouble()
    val inputUnit = scanner.next()
    val (resolvedUnit, convertedValue) = convert(inputUnit.toLowerCase(), inputValue)
    println("$inputValue $resolvedUnit is $convertedValue meter${if (convertedValue == 1.0) "" else "s"}")
//    convertCentimetersToMeters(145)
//    convertMilesToKilometers(2)
//    convertInchesToMillimeters(5.5)
//    convertDegreesToFahrenheit(12)
//    convertPoundsToKilos(3)
}

data class Measures(val unit: String, val value: Double)

fun convert(inputUnit: String?, inputMeters: Double): Measures {

    when {
        meterUnits.contains(inputUnit) -> {
            val unit = if (inputMeters == 1.0) meterUnits[0] else meterUnits[1]
            return Measures(unit, (inputMeters * 1))
        }
        kilometersUnits.contains(inputUnit) -> {
            return Measures(if (inputMeters == 1.0) kilometersUnits[0] else kilometersUnits[1], (inputMeters * METERS_PER_KILOMETER))
        }
        centimeterUnits.contains(inputUnit) -> {
            return Measures(if (inputMeters == 1.0) centimeterUnits[0] else centimeterUnits[1], (inputMeters * METERS_PER_CENTIMETER))
        }
        millimeterUnits.contains(inputUnit) -> {
            return Measures(if (inputMeters == 1.0) millimeterUnits[0] else millimeterUnits[1], (inputMeters * METERS_PER_MILLIMETER))
        }
        mileUnits.contains(inputUnit) -> {
            return Measures(if (inputMeters == 1.0) mileUnits[0] else mileUnits[1], (inputMeters * METERS_PER_MILE))
        }
        feetUnits.contains(inputUnit) -> {
            return Measures(if (inputMeters == 1.0) feetUnits[0] else feetUnits[1], (inputMeters * METERS_PER_FOOT))
        }
        inchesUnits.contains(inputUnit) -> {
            return Measures(if (inputMeters == 1.0) inchesUnits[0] else inchesUnits[1], (inputMeters * METERS_PER_INCH))
        }
        yardUnits.contains(inputUnit) -> {
            return Measures(if (inputMeters == 1.0) yardUnits[0] else yardUnits[1], (inputMeters * METERS_PER_YARD))
        }
        else -> return Measures("", 1.0)
    }
}

fun toMeters(kilometers: Int): Int {
    return kilometers * METERS_PER_KILOMETER
}

fun convertPoundsToKilos(pounds: Int) {
    val kilos = pounds.toDouble() * 0.453592
    println("$pounds pounds is $kilos kilograms")
}

fun convertDegreesToFahrenheit(degrees: Int) {
    val fahrenheit = degrees.toDouble() * (9.toDouble() / 5.toDouble()) + 32
    println("$degrees degrees Celsius is $fahrenheit degrees Fahrenheit")
}

fun convertInchesToMillimeters(inches: Double) {
    val millis = inches * 25.4
    println("$inches inches is $millis millimeters")
}

fun convertMilesToKilometers(miles: Int) {
    val kilometers = miles.toDouble() * 1.60934
    println("$miles miles is ${"%.4f".format(kilometers)} kilometers")
}

fun convertCentimetersToMeters(centimeters: Int) {
    val meters = centimeters.toDouble() / 100
    println("$centimeters centimeters is $meters meters")
}
