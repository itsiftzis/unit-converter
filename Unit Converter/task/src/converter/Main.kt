package converter

import java.util.*
import kotlin.system.exitProcess

private val unitsToGramsTable = mapOf(MeasurementUnits.GRAM to 1.0, MeasurementUnits.GRAMS to 1.0, MeasurementUnits.G to 1.0, MeasurementUnits.KILOGRAM to 1000.0,
        MeasurementUnits.KG to 1000.0, MeasurementUnits.KILOGRAMS to 1000.0, MeasurementUnits.MILLIGRAM to 0.001, MeasurementUnits.MILLIGRAMS to 0.001, MeasurementUnits.MG to 0.001, MeasurementUnits.POUND to 453.592, MeasurementUnits.POUNDS to 453.592, MeasurementUnits.LB to 453.592, MeasurementUnits.OUNCE to 28.3495, MeasurementUnits.OUNCES to 28.3495, MeasurementUnits.OZ to 28.3495,
        MeasurementUnits.METER to 1.0, MeasurementUnits.METERS to 1.0, MeasurementUnits.M to 1.0, MeasurementUnits.CENTIMETER to 0.01, MeasurementUnits.CENTIMETERS to 0.01, MeasurementUnits.CM to 0.01, MeasurementUnits.MILLIMETER to 0.001, MeasurementUnits.MILLIMETERS to 0.001, MeasurementUnits.MM to 0.001, MeasurementUnits.YARD to 0.9144, MeasurementUnits.YARDS to 0.9144, MeasurementUnits.YD to 0.9144, MeasurementUnits.MILE to 1609.34, MeasurementUnits.MILES to 1609.34, MeasurementUnits.MI to 1609.34, MeasurementUnits.FOOT to 0.3048, MeasurementUnits.FEET to 0.3048, MeasurementUnits.FT to 0.3048, MeasurementUnits.INCH to 0.0254, MeasurementUnits.INCHES to 0.0254, MeasurementUnits.IN to 0.0254)
private val gramsToReferenceUnitTable = mapOf(MeasurementUnits.GRAM to 1.0, MeasurementUnits.KILOGRAM to 0.001, MeasurementUnits.MILLIGRAM to 1000.0, MeasurementUnits.POUND to 1.0.div(453.592), MeasurementUnits.OUNCE to 1.0.div(28.3495),
        MeasurementUnits.METER to 1.0, MeasurementUnits.KILOMETER to 0.001, MeasurementUnits.CENTIMETER to 100.0, MeasurementUnits.MILLIMETER to 1000.0, MeasurementUnits.YARD to 1.0.div(0.9144), MeasurementUnits.MILE to 1.0.div(0.3048), MeasurementUnits.INCH to 1.0.div(0.0254))

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
            if (sourceUnit.startsWith("degree")) {
                val sourceUnitName = scanner.next()
            } else {
                scanner.next()
                val targetUnit = scanner.next()
                if (targetUnit.startsWith("degree")) {
                    val targetUnitName = scanner.next()
                } else {
                    val convertedAmount = convertToReferenceUnit(doubleSourceAmount, sourceUnit.toUpperCase())
                    val targetUnitsConverted = convertToUnit(convertedAmount.split(" ")[0].toDouble(), targetUnit.toUpperCase())
                    println("$doubleSourceAmount ${convertedAmount.split(" ")[1]} is $targetUnitsConverted")
                    println("Enter what you want to convert (or exit):")
                }
            }
        }
    }
}

fun convertToUnit(convertedVolume: Double, targetUnit: String): String {
    return when {
        MeasurementUnits.values().contains(MeasurementUnits.valueOf(targetUnit)) -> {
            val result = convertedVolume.times(gramsToReferenceUnitTable[MeasurementUnits.valueOf(targetUnit.removeSuffix("S"))]
                    ?: 1.0)
            result.toString() + " " + if (result == 1.0) MeasurementUnits.valueOf(targetUnit).defaultValue else MeasurementUnits.valueOf(targetUnit).defaultValue + "s"
        }
        else -> "$convertedVolume"
    }
}

fun convertToReferenceUnit(sourceAmount: Double, sourceUnit: String): String {
    return when {
        MeasurementUnits.values().contains(MeasurementUnits.valueOf(sourceUnit)) -> {
            sourceAmount.times(unitsToGramsTable[MeasurementUnits.valueOf(sourceUnit.removeSuffix("" +
                    "S"))]
                    ?: 1.0).toString() + " " + if (sourceAmount == 1.0) MeasurementUnits.valueOf(sourceUnit).defaultValue else MeasurementUnits.valueOf(sourceUnit).defaultValue + "s"
        }
        else -> {
            "$sourceAmount"
        }
    }
}