package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component


@Component
class Day01(
    private val fileReader: FileReader
) {
    companion object {
        val digitList = listOf("1","2","3","4","5","6","7","8","9","one","two","three","four","five","six","seven","eight","nine")
    }

    fun solvePartA(fileName: String) = fileReader
        .readStringsFromFile(fileName)
        .sumOf { charArrayOf(it.getFirstDigit(), it.getLastDigit()).joinToString("").toLong() }

    fun solvePartB(fileName: String) = fileReader
        .readStringsFromFile(fileName)
        .sumOf { charArrayOf(it.getFirstDigitWithStrings(), it.getLastDigitWithStrings()).joinToString("").toLong() }

    private fun String.getFirstDigit() = this.first { it.isDigit() }

    private fun String.getLastDigit() = this.last { it.isDigit() }

    private fun String.getFirstDigitWithStrings() = this
        .findAnyOf(digitList)
        .getDigit()

    private fun String.getLastDigitWithStrings() = this
        .findLastAnyOf(digitList)
        .getDigit()

    private fun Pair<Int, String>?.getDigit() = this
        ?.second
        ?.mapToDigit()
        ?.firstOrNull() ?: throw Exception("Error")

    private fun String.mapToDigit() = when(this) {
        "one" -> "1"
        "two" -> "2"
        "three" -> "3"
        "four" -> "4"
        "five" -> "5"
        "six" -> "6"
        "seven" -> "7"
        "eight" -> "8"
        "nine" -> "9"
        else -> this
    }

}