package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component
import kotlin.math.pow
import kotlin.math.sign

@Component
class Day04(
    private val fileReader: FileReader
) {
    fun solvePartA(fileName: String = "day04") = getScratchCards(fileName = fileName)
        .sumOf {
            when (it.size) {
                0 -> 0L
                1 -> 1L
                else -> 2.0.pow((it.size-1).toDouble()).toLong()
            }
        }

    fun solvePartB(fileName: String = "day04"): Int {
        var copyMap = mutableMapOf<Int, Int>() // Index, copies

        getScratchCards(fileName = fileName)
            .map { it.size }
            .forEachIndexed { index, size ->
                // Card itself
                copyMap[index] = copyMap.getOrDefault(index, 0) + 1

                // Other cards
                (index+1..index+size).forEach { i ->
                    copyMap[i] = copyMap.getOrDefault(i, 0) + copyMap.getOrDefault(index, 1)
                }
            }

        return copyMap.values.sum()
    }

    private fun getScratchCards(fileName: String) = fileReader
        .readStringsFromFile(fileName)
        .map {
            val (first, second) = it.substringAfter(": ")
                .split(" | ")
                .map { it.split(" ").mapNotNull { it.toLongOrNull() } }
            Pair(first, second)
        }
        .map { pair -> pair.first.filter { it in pair.second } }
}
