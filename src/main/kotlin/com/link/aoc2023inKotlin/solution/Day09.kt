package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component

@Component
class Day09(
    private val fileReader: FileReader
) {
    fun solvePartA(fileName: String = "day09") = fileReader
        .readStringsFromFile(fileName)
        .map { it.toLongList() }
        .sumOf { predictNextValue(sequence = it) }

    fun solvePartB(fileName: String = "day09") = fileReader
        .readStringsFromFile(fileName)
        .map { it.toLongList() }
        .sumOf { predictBeforeValue(sequence = it) }

    fun predictNextValue(sequence: List<Long>): Long {
        val differences = sequence.zipWithNext { a, b -> b-a }

        return when (differences.all { it == 0L }) {
            true -> sequence.last()
            else -> sequence.last() + predictNextValue(sequence = differences)
        }
    }

    fun predictBeforeValue(sequence: List<Long>): Long {
        val differences = sequence.zipWithNext { a, b -> b-a }

        return when (differences.all { it == 0L }) {
            true -> sequence.first()
            else -> sequence.first() - predictBeforeValue(sequence = differences)
        }
    }

    private fun String.toLongList() = this
        .split(" ")
        .map { it.toLong() }
}