package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.model.CubeGame
import com.link.aoc2023inKotlin.model.CubeGameRound
import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component


@Component
class Day02(
    private val fileReader: FileReader
) {
    fun solvePartA(fileName: String) = fileReader
        .readStringsFromFile(fileName)
        .map { it.toCubeGame() }
        .filter { it.containsMaxCubes(red = 12, green = 13, blue = 14) }
        .sumOf { it.id }

    fun solvePartB(fileName: String) = fileReader
        .readStringsFromFile(fileName)
        .map { it.toCubeGame() }
        .sumOf { it.maxNumberOfRedCubes() * it.maxNumberOfGreenCubes() * it.maxNumberOfBlueCubes() }

    fun String.toCubeGame() = CubeGame(
        id = this.substringBefore(":").substringAfter("Game ").toLong(),
        cubeGameRounds = this.substringAfter(":").replace(" ", "").split(";").map { it.toCubeGameRound() }
    )

    private fun String.toCubeGameRound() = CubeGameRound(
        red = this.toCubeCount(color="red"),
        green = this.toCubeCount(color="green"),
        blue = this.toCubeCount(color="blue")
    )

    private fun String.toCubeCount(color: String) = when(this.contains(color)) {
        true -> this.substringBefore(color).substringAfterLast(",").toInt()
        else -> 0
    }
}