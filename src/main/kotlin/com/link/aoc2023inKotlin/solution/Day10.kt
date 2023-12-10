package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.model.Coordinate
import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component
import kotlin.math.max

@Component
class Day10(
    private val fileReader: FileReader
) {

    fun solvePartA(fileName: String = "day10"): Long {
        var inputMap = loadInputMap(fileName = fileName)
        val startPosition = findPositionOf(char = 'S', inputMap=inputMap)
        inputMap = replaceStartPosition(inputMap = inputMap, startPosition = startPosition)

        return findMaxSteps(inputMap = inputMap, startPos = startPosition).first
    }

    fun solvePartB(fileName: String = "day10"): Int {
        var inputMap = loadInputMap(fileName = fileName)
        val startPosition = findPositionOf(char = 'S', inputMap = inputMap)
        inputMap = replaceStartPosition(inputMap = inputMap, startPosition = startPosition)
        val loopTmp = findMaxSteps(inputMap = inputMap, startPos = startPosition).second

        val points = inputMap
            .flatMapIndexed { y, chars -> chars.mapIndexed { x, c -> Triple(x,y,c) } }
            .filter { it !in loopTmp }
            .map { Pair(it.first, it.second) }

        val loop = loopTmp.filter { it.third != '-' }

        return points.count { isInLoop(it, loop) }
    }

    private fun isInLoop(
        point: Pair<Int, Int>,
        inputMap: List<Triple<Int, Int, Char>>
    ): Boolean {
        val inputs = inputMap.filter { loopCoord -> loopCoord.second == point.second }.sortedBy { it.first }
        var counter = 0

        (0..point.first)
            .reversed()
            .forEach {
                val tmp = inputs
                    .mapIndexed { index, triple -> Pair(index,triple) }
                    .firstOrNull{ loopCoord -> loopCoord.second.first == it }

                val actualIndex = tmp?.first ?: -1
                val actualSymbol = tmp?.second?.third
                val preSymbol = inputs.getOrNull(actualIndex-1)?.third

                if (actualSymbol == '|') {
                    counter++
                } else if (preSymbol == 'L' && actualSymbol == '7') {
                    counter++
                } else if (preSymbol == 'F' && actualSymbol == 'J') {
                    counter++
                }
            }

        if (counter % 2 == 1) {
            println(point)
        }

        return counter % 2 == 1
    }


    private fun findMaxSteps(
        inputMap: Array<CharArray>,
        startPos: Coordinate
    ): Pair<Long, List<Triple<Int, Int, Char>>> {
        val valuesToCheck = mutableListOf(Pair(Coordinate(startPos.x, startPos.y), 0L))
        val alreadyVisited = mutableListOf<Coordinate>()
        var maxSteps = 0L

        while (valuesToCheck.isNotEmpty()) {
            val currentPosition = valuesToCheck.removeFirst()
            val currentSymbol = inputMap.getOrNull(currentPosition.first.y)?.getOrNull(currentPosition.first.x)

            if (currentSymbol == '.') {
                continue
            } else if (currentPosition.first in alreadyVisited) {
                continue
            }

            alreadyVisited.add(currentPosition.first)

            val (nextPositionA, nextPositionB) = findNextPositions(
                startPosition = Coordinate(currentPosition.first.x, currentPosition.first.y),
                symbol = inputMap[currentPosition.first.y][currentPosition.first.x]
            )

            valuesToCheck.add(Pair(nextPositionA, currentPosition.second + 1))
            valuesToCheck.add(Pair(nextPositionB, currentPosition.second + 1))

            maxSteps = max(maxSteps, currentPosition.second)
        }

        return Pair(maxSteps, alreadyVisited.map { Triple(it.x, it.y, inputMap[it.y][it.x]) })
    }

    private fun replaceStartPosition(
        inputMap: Array<CharArray>,
        startPosition: Coordinate
    ): Array<CharArray> {
        val north   = "|7F".contains( inputMap.getOrNull(startPosition.y-1)?.getOrNull(startPosition.x) ?: '.' )
        val west    = "-LF".contains( inputMap.getOrNull(startPosition.y)?.getOrNull(startPosition.x-1) ?: '.' )
        val south   = "|LJ".contains( inputMap.getOrNull(startPosition.y+1)?.getOrNull(startPosition.x) ?: '.' )
        val east    = "-J7".contains( inputMap.getOrNull(startPosition.y)?.getOrNull(startPosition.x+1) ?: '.')

        val symbol = when {
            north && south  -> '|'
            east && west    -> '-'
            north && east   -> 'L'
            north && west   -> 'J'
            south && west   -> '7'
            south && east   -> 'F'
            else -> throw Exception("Error")
        }

        return inputMap.map { it.joinToString("").replace('S', symbol).toCharArray() }.toTypedArray()

    }

    private fun findNextPositions(
        startPosition: Coordinate,
        symbol: Char
    ) = when (symbol) {
            '|' -> Pair(Coordinate(startPosition.x, startPosition.y + 1), Coordinate(startPosition.x, startPosition.y - 1))
            '-' -> Pair(Coordinate(startPosition.x + 1, startPosition.y), Coordinate(startPosition.x - 1, startPosition.y))
            'L' -> Pair(Coordinate(startPosition.x, startPosition.y - 1 ), Coordinate(startPosition.x + 1, startPosition.y))
            'J' -> Pair(Coordinate(startPosition.x, startPosition.y - 1 ), Coordinate(startPosition.x - 1, startPosition.y))
            '7' -> Pair(Coordinate(startPosition.x, startPosition.y + 1 ), Coordinate(startPosition.x - 1, startPosition.y))
            'F' -> Pair(Coordinate(startPosition.x, startPosition.y + 1 ), Coordinate(startPosition.x + 1, startPosition.y))
            else -> throw Exception("Symbol is invalid")
        }

    private fun loadInputMap(fileName: String) = fileReader
        .readStringsFromFile(fileName)
        .map { it.toCharArray() }
        .toTypedArray()

    private fun findPositionOf(
        char: Char,
        inputMap: Array<CharArray>
    ): Coordinate {
        val yPos = inputMap
            .mapIndexedNotNull { index, chars -> when(chars.contains(char)) {
                true -> index
                else -> null
            } }
            .single()

        val xPos = inputMap[yPos].indexOf(char)

        return Coordinate(xPos, yPos)
    }
}