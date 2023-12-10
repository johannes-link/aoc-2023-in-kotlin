package com.link.aoc2023inKotlin.solution

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
        val loopTmp = findMaxSteps(inputMap = inputMap, startPos = startPosition)
            .second

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
        startPos: Pair<Int, Int>
    ): Pair<Long, MutableList<Triple<Int, Int, Char>>> {
        val valuesToCheck = mutableListOf(Pair(startPos, 0L))
        val alreadyVisited = mutableListOf<Pair<Int, Int>>()
        var maxSteps = 0L

        while (valuesToCheck.isNotEmpty()) {
            val currentPosition = valuesToCheck.removeFirst()
            val currentSymbol = inputMap.getOrNull(currentPosition.first.second)?.getOrNull(currentPosition.first.first)

            if (currentSymbol == '.') {
                continue
            } else if (currentPosition.first in alreadyVisited) {
                continue
            }

            alreadyVisited.add(currentPosition.first)

            val (nextPositionA, nextPositionB) = findNextPositions(
                startPosition = currentPosition.first,
                inputMap = inputMap
            )

            valuesToCheck.add(Pair(nextPositionA, currentPosition.second + 1))
            valuesToCheck.add(Pair(nextPositionB, currentPosition.second + 1))

            maxSteps = max(maxSteps, currentPosition.second)
        }

        return Pair(maxSteps, alreadyVisited.map { Triple(it.first, it.second, inputMap[it.second][it.first]) }.toMutableList())
    }

    private fun replaceStartPosition(
        inputMap: Array<CharArray>,
        startPosition: Pair<Int, Int>
    ): Array<CharArray> {
        val (x, y)  = startPosition
        val north   = "|7F".contains( inputMap.getOrNull(y-1)?.getOrNull(x) ?: '.' )
        val west    = "-LF".contains( inputMap.getOrNull(y)?.getOrNull(x-1) ?: '.' )
        val south   = "|LJ".contains( inputMap.getOrNull(y+1)?.getOrNull(x) ?: '.' )
        val east    = "-J7".contains( inputMap.getOrNull(y)?.getOrNull(x+1) ?: '.')

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
        startPosition: Pair<Int, Int>,
        inputMap: Array<CharArray>
    ) = when (val currentSymbol = inputMap[startPosition.second][startPosition.first]) {
            '|' -> Pair(Pair(startPosition.first, startPosition.second + 1), Pair(startPosition.first, startPosition.second - 1))
            '-' -> Pair(Pair(startPosition.first + 1, startPosition.second), Pair(startPosition.first - 1, startPosition.second))
            'L' -> Pair(Pair(startPosition.first, startPosition.second - 1 ), Pair(startPosition.first + 1, startPosition.second))
            'J' -> Pair(Pair(startPosition.first, startPosition.second - 1 ), Pair(startPosition.first - 1, startPosition.second))
            '7' -> Pair(Pair(startPosition.first, startPosition.second + 1 ), Pair(startPosition.first - 1, startPosition.second))
            'F' -> Pair(Pair(startPosition.first, startPosition.second + 1 ), Pair(startPosition.first + 1, startPosition.second))
            else -> throw Exception("$currentSymbol is invalid")
        }

    private fun loadInputMap(fileName: String) = fileReader
        .readStringsFromFile(fileName)
        .map { it.toCharArray() }
        .toTypedArray()

    private fun findPositionOf(
        char: Char,
        inputMap: Array<CharArray>
    ): Pair<Int, Int> {
        val yPos = inputMap
            .mapIndexedNotNull { index, chars -> when(chars.contains(char)) {
                true -> index
                else -> null
            } }
            .single()

        val xPos = inputMap[yPos].indexOf(char)

        return Pair(xPos, yPos)
    }
}