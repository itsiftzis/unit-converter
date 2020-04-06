package converter

import java.util.*
import kotlin.system.exitProcess

//mass units
private val gramAliases = listOf("gram", "grams", "g")
private val kilogramAliases = listOf("kilogram", "kilograms", "kg")
private val milligramAliases = listOf("milligram", "milligrams", "mg")
private val poundAliases = listOf("pound", "pounds", "lb")
private val ounceAliases = listOf("ounce", "ounces", "oz")

//distance units
private val meterAliases = listOf("meter", "meters", "m")
private val kilometerAliases = listOf("kilometer", "kilometers", "km")
private val centimeterAliases = listOf("centimeter", "centimeters", "cm")
private val millimeterAliases = listOf("millimeter", "millimeters", "mm")
private val yardAliases = listOf("yard", "yards", "yd")
private val mileAliases = listOf("mile", "miles", "mi")
private val footAliases = listOf("foot", "feet", "ft")
private val inchAliases = listOf("inch", "inches", "in")

private val unitsToGramsTable = mapOf("gram" to 1.0, "kilogram" to 1000.0, "milligram" to 0.001, "pound" to 453.592, "ounce" to 28.3495)
private val gramsToReferenceUnitTable = mapOf("gram" to 1.0, "kilogram" to 0.001, "milligram" to 1000.0, "pound" to 1.0.div(453.592), "ounce" to 1.0.div(28.3495))
private val unitsToMetersTable = mapOf("meter" to 1.0, "kilometer" to 1000.0, "centimeter" to 0.01, "millimeter" to 0.001, "yard" to 0.9144, "mile" to 1609.34, "foot" to 0.3048, "inch" to 0.0254)
private val metersToReferenceUnitTable = mapOf("meter" to 1.0, "kilometer" to 0.001, "centimeter" to 100.0, "millimeter" to 1000.0, "yard" to 1.0.div(0.9144), "mile" to 1.0.div(0.3048), "inch" to 1.0.div(0.0254))

fun main() {
    val scanner = Scanner(System.`in`)
    println("Enter what you want to convert (or exit):")
    while (true) {
        val sourceAmount = scanner.next()
        if (sourceAmount == "exit") {
            exitProcess(0)
        } else {
            val doubleSourceAmount = sourceAmount.toDouble()
            val sourceUnit = scanner.next()

            scanner.next()
            val targetUnit = scanner.next()

            val convertedAmount = convertToReferenceUnit(doubleSourceAmount, sourceUnit.toLowerCase())
            val targetUnitsConverted = convertToUnit(convertedAmount.split(" ")[0].toDouble(), targetUnit.toLowerCase())
            println("$doubleSourceAmount ${convertedAmount.split(" ")[1]} is $targetUnitsConverted")
            println("Enter what you want to convert (or exit):")
        }
    }
}

fun convertToUnit(convertedVolume: Double, targetUnit: String?): String {
    return when {
        gramAliases.contains(targetUnit) -> {
            val result = convertedVolume.times(gramsToReferenceUnitTable[gramAliases[0]] ?: 1.0)
            result.toString() + " " + plurality(result, gramAliases)
        }
        kilogramAliases.contains(targetUnit) -> {
            val result = convertedVolume.times(gramsToReferenceUnitTable[kilogramAliases[0]]
                    ?: 1.0)
            result.toString() + "  " + plurality(result, kilogramAliases)
        }
        milligramAliases.contains(targetUnit) -> {
            val result = convertedVolume.times(gramsToReferenceUnitTable[milligramAliases[0]]
                    ?: 1.0)
            result.toString() + " " + plurality(result, milligramAliases)
        }
        poundAliases.contains(targetUnit) -> {
            val result = convertedVolume.times(gramsToReferenceUnitTable[poundAliases[0]] ?: 1.0)
            result.toString() + " " + plurality(result, poundAliases)
        }
        ounceAliases.contains(targetUnit) -> {
            val result = convertedVolume.times(
                    gramsToReferenceUnitTable[ounceAliases[0]] ?: 1.0)
            result.toString() + " " + plurality(result, ounceAliases)
        }
        yardAliases.contains(targetUnit) -> {
            val result = convertedVolume.times(metersToReferenceUnitTable[yardAliases[0]] ?: 1.0)
            result.toString() + " " + plurality(result, yardAliases)
        }
        meterAliases.contains(targetUnit) -> {
            val result = convertedVolume.times(metersToReferenceUnitTable[meterAliases[0]] ?: 1.0)
            result.toString() + " " + plurality(result, meterAliases)
        }
        centimeterAliases.contains(targetUnit) -> {
            val result = convertedVolume.times(metersToReferenceUnitTable[centimeterAliases[0]] ?: 1.0)
            result.toString() + " " + plurality(result, centimeterAliases)
        }
        footAliases.contains(targetUnit) -> {
            val result = convertedVolume.times(metersToReferenceUnitTable[footAliases[0]] ?: 1.0)
            result.toString() + " " + plurality(result, footAliases)
        }
        inchAliases.contains(targetUnit) -> {
            val result = convertedVolume.times(metersToReferenceUnitTable[inchAliases[0]] ?: 1.0)
            result.toString() + " " + plurality(result, inchAliases)
        }
        millimeterAliases.contains(targetUnit) -> {
            val result = convertedVolume.times(metersToReferenceUnitTable[millimeterAliases[0]] ?: 1.0)
            result.toString() + " " + plurality(result, millimeterAliases)
        }
        else -> "$convertedVolume"
    }
}

fun convertToReferenceUnit(sourceAmount: Double, sourceUnit: String?): String {
    return when {
        gramAliases.contains(sourceUnit) -> {
            sourceAmount.times(unitsToGramsTable[gramAliases[0]]
                    ?: 1.0).toString() + " " + plurality(sourceAmount, gramAliases)
        }
        kilogramAliases.contains(sourceUnit) -> {
            sourceAmount.times(unitsToGramsTable[kilogramAliases[0]]
                    ?: 1.0).toString() + " " + plurality(sourceAmount, kilogramAliases)
        }
        milligramAliases.contains(sourceUnit) -> {
            sourceAmount.times(
                    unitsToGramsTable[milligramAliases[0]]
                            ?: 1.0).toString() + " " + plurality(sourceAmount, milligramAliases)
        }
        poundAliases.contains(sourceUnit) -> {
            sourceAmount.times(unitsToGramsTable[poundAliases[0]]
                    ?: 1.0).toString() + " " + plurality(sourceAmount, poundAliases)
        }
        ounceAliases.contains(sourceUnit) -> {
            sourceAmount.times(unitsToGramsTable[ounceAliases[0]]
                    ?: 1.0).toString() + " " + plurality(sourceAmount, ounceAliases)
        }
        yardAliases.contains(sourceUnit) -> {
            sourceAmount.times(unitsToMetersTable[yardAliases[0]]
                    ?: 1.0).toString() + " " + plurality(sourceAmount, yardAliases)
        }
        meterAliases.contains(sourceUnit) -> {
            sourceAmount.times(unitsToMetersTable[meterAliases[0]]
                    ?: 1.0).toString() + " " + plurality(sourceAmount, meterAliases)
        }
        centimeterAliases.contains(sourceUnit) -> {
            sourceAmount.times(unitsToMetersTable[centimeterAliases[0]]
                    ?: 1.0).toString() + " " + plurality(sourceAmount, centimeterAliases)
        }
        footAliases.contains(sourceUnit) -> {
            sourceAmount.times(unitsToMetersTable[footAliases[0]]
                    ?: 1.0).toString() + " " + plurality(sourceAmount, footAliases)
        }
        inchAliases.contains(sourceUnit) -> {
            sourceAmount.times(unitsToMetersTable[inchAliases[0]]
                    ?: 1.0).toString() + " " + plurality(sourceAmount, inchAliases)
        }
        millimeterAliases.contains(sourceUnit) -> {
            sourceAmount.times(unitsToMetersTable[millimeterAliases[0]]
                    ?: 1.0).toString() + " " + plurality(sourceAmount, millimeterAliases)
        }
        else -> {
            "$sourceAmount"
        }
    }
}

private fun plurality(sourceAmount: Double, aliases: List<String>) =
        if (sourceAmount == 1.0) aliases[0] else aliases[1]