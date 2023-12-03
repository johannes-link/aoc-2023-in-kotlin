package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component

@Component
class Day03(
    private val fileReader: FileReader
) {
    fun solvePartA(fileName: String = "day03"): Long {
        val (numbers, symbols) = getNumbersAndSymbols(fileName = fileName)

        return numbers
            .filter { it.isAdjacentToSymbols(symbols) }
            .sumOf { it.first.toLong() }
    }

    fun solvePartB(fileName: String = "day03"): Long {
        val (numbers, symbols) = getNumbersAndSymbols(fileName = fileName)

        return symbols
            .filter { it.first == "*" }
            .sumOf { it.calculateGear(numbers = numbers) }
    }

    private fun getNumbersAndSymbols(fileName: String): Pair<MutableSet<Triple<String, Int, Int>>, MutableSet<Triple<String, Int, Int>>> {
        val numbers = mutableSetOf<Triple<String, Int, Int>>()
        val symbols = mutableSetOf<Triple<String, Int, Int>>()
        val inputs = fileReader
            .readStringsFromFile(fileName)
            .flatMapIndexed { y, line -> line.mapIndexed { x, char ->  Triple(char.toString(), x, y)} }
            .filter { it.first != "." }

        var tmp = inputs.first()
        inputs.forEachIndexed { index, triple ->
            if (inputs[index].first.toIntOrNull() == null) { // If actual value is a symbol
                numbers.add(tmp)
                symbols.add(inputs[index])
                return@forEachIndexed
            }

            if (inputs[index].second == tmp.second+1) {
                tmp = Triple(tmp.first + inputs[index].first, inputs[index].second, inputs[index].third)
            } else {
                numbers.add(tmp)
                tmp = inputs[index]
            }
        }
        numbers.add(tmp)

        return Pair(numbers, symbols)
    }

    private fun Triple<String, Int, Int>.isAdjacentToSymbols(symbols: MutableSet<Triple<String, Int, Int>>) = symbols
        .any { (it.second in (this.getXStartIndex()-1..this.second+1)) && it.third in (this.third-1..this.third+1) }

    private fun Triple<String, Int, Int>.getXStartIndex() = this.second+1-this.first.length

    private fun Triple<String, Int, Int>.calculateGear(numbers: MutableSet<Triple<String, Int, Int>>): Long {
        val adjacentNumbers = numbers
            .filter { it.isAdjacentToSymbols(mutableSetOf(this)) }

        return when (adjacentNumbers.size) {
            2 -> adjacentNumbers.first().first.toLong() * adjacentNumbers.last().first.toLong()
            else -> 0L
        }
    }
}
