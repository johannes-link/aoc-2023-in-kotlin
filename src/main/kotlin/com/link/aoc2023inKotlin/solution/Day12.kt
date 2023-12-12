package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.model.CubeGame
import com.link.aoc2023inKotlin.model.CubeGameRound
import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component

@Component
class Day12(
    private val fileReader: FileReader
) {
    fun solvePartA(fileName: String = "day12") = fileReader
        .readStringsFromFile(fileName)
        .map { it.toArrangement() }
        .sumOf { it.numberOfPossibleArrangements() }

    fun solvePartB(fileName: String = "day12") = fileReader
        .readStringsFromFile(fileName)
        .map { it.toArrangement() }
        .map{ unfold(it) }
        .sumOf { it.numberOfPossibleArrangements() }

    fun unfold(input: Pair<String, List<Int>>): Pair<String, List<Int>> {
        val first = (0..<5).joinToString("") { input.first }
        val second = (0..<5).flatMap { input.second }
        return Pair(first, second)
    }
}

fun String.toArrangement(): Pair<String, List<Int>> {
    val (conditionRecord, groupSizes) = this.split(" ")
    return Pair(conditionRecord, groupSizes.split(",").map { it.toInt() })
}

fun Pair<String, List<Int>>.numberOfPossibleArrangements(): Long {
    val regex = "[.?]*" + second.joinToString("") { "[#?]{$it}[.?]+" }.reversed().replaceFirst("+", "*").reversed()

    return findAllArrangements(first).count { Regex(regex).matches(it) }.toLong()
}

fun findAllArrangements(input: String): List<String> {
    println("Beginne mit $input")
    if (input.contains("?")) {
        val first = findAllArrangements(input.replaceFirst("?", "."))
        val second = findAllArrangements(input.replaceFirst("?", "#"))

        return listOf(first, second).flatten()
    }

    println("Ende :)")

    return listOf(input)
}