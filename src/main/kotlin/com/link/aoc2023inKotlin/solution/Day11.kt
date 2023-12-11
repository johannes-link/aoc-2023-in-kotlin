package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component
import kotlin.math.max

@Component
class Day11(
    private val fileReader: FileReader
) {

    fun solvePartA(fileName: String = "day11") = solveDay(fileName = fileName, steps = 1)

    fun solvePartB(fileName: String = "day11", steps: Int=1000000) = solveDay(fileName = fileName, steps = steps)


    private fun solveDay(fileName: String = "day11", steps: Int = 1): Long {
        val universe = fileReader.readStringsFromFile(fileName)
        val coordinatesToExtend = extendUniverse(universe = universe)

        val coordinates = universe
            .flatMapIndexed { y, string -> string.mapIndexed { x, char -> Coordinate(x=x.toLong(), y=y.toLong(), symbol=char)  } }
            .filter { it.symbol == '#' }

        return coordinates
            .mapIndexed { index, coordinate -> coordinate.calculatedDistances(coordinates = coordinates, coordinatesToExtend = coordinatesToExtend, index = index, steps = steps)}
            .sum()
    }

    fun extendUniverse(universe: List<String>): Pair<List<Int>, List<Int>> {
        val xLines = mutableListOf<Int>()
        val yLines = mutableListOf<Int>()

        // x - axis
        universe
            .forEachIndexed { y, it ->
                if (it.all { char -> char == '.' }) {
                    yLines.add(y)
                }
            }

        // y - axis
        for (x in universe[0].indices) {
            var allPoints = true
            for (y in universe.indices) {
                if (universe[y][x] != '.') {
                    allPoints = false
                    break
                }
            }

            if (allPoints) {
                xLines.add(x)
            }
        }

        return Pair(xLines, yLines)
    }
}

data class Coordinate(
    val x: Long,
    val y: Long,
    val symbol: Char
)

fun Coordinate.calculatedDistances(
    coordinates: List<Coordinate>,
    coordinatesToExtend: Pair<List<Int>, List<Int>>,
    index: Int,
    steps: Int = 1
): Long {
    return coordinates.subList(index, coordinates.size).sumOf {
        val (minX, maxX) = listOf(this.x, it.x).sorted()
        val (minY, maxY) = listOf(this.y, it.y).sorted()

        var result = (maxX - minX) + (maxY - minY)

        coordinatesToExtend.first.forEach { value ->
            if (value in (minX..maxX)) {
                result+= max(steps-1, 1)
            }
        }

        coordinatesToExtend.second.forEach { value ->
            if (value in (minY..maxY)) {
                result+=max(steps-1, 1)
            }
        }

        result
    }
}
