package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.model.CamelCardHand
import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component

@Component
class Day07(
    private val fileReader: FileReader
) {
    fun solvePartA(fileName: String = "day07") = fileReader
        .readStringsFromFile(fileName)
        .map { it.toCamelCard() }
        .sorted()
        .mapIndexed { index, camelCardHand -> (index+1) * camelCardHand.bid.toLong() }
        .sum()

    fun solvePartB(fileName: String = "day07") = fileReader
        .readStringsFromFile(fileName)
        .map { it.replace('J', 'X') } // X is the Joker
        .map { it.toCamelCard() }
        .sorted()
        .mapIndexed { index, camelCardHand -> (index+1) * camelCardHand.bid.toLong() }
        .sum()

    private fun String.toCamelCard() = this
        .split(" ")
        .let { CamelCardHand(it[0], it[1].toInt()) }
}