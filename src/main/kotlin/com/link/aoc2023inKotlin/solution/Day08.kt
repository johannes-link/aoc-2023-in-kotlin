package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component
import java.math.BigInteger
import kotlin.math.abs

@Component
class Day08(
    private val fileReader: FileReader
) {
    fun solvePartA(fileName: String = "day08"): Long {
        val (instructions, network) = this.parseInputFile(fileName = fileName)
        var steps = 1L
        var index = 0
        var start = "AAA"

        while (true) {
            start = findNewStartPosition(key = start, instruction = instructions[index], network = network)

            if (start == "ZZZ") {
                return steps
            }

            when (index == instructions.length-1) {
                true -> index = 0
                else -> index++
            }

            steps++
        }
    }

    fun solvePartB(fileName: String = "day08"): BigInteger {
        val (instructions, network) = this.parseInputFile(fileName = fileName)
        return network
            .keys
            .filter { it.last() == 'A' }
            .map { findLoopSize(startPosition=it, instructions=instructions, network=network) }
            .let { findLeastCommonMultiple(values = it) }
    }

    private fun findLeastCommonMultiple(values: List<Long>) = values
        .map { BigInteger.valueOf(it) }
        .reduce { acc, bigInteger -> acc * bigInteger / acc.gcd(bigInteger) }

    private fun findLoopSize(
        startPosition: String,
        instructions: String,
        network: Map<String, Pair<String, String>>
    ): Long {
        var index = 0
        var stepsSinceZ = 0L
        var start = startPosition

        while (true) {
            start = findNewStartPosition(key = start, instruction = instructions[index], network = network)
            stepsSinceZ++


            if (start.endsWith('Z')) {
                return stepsSinceZ
            }

            when (index == instructions.length-1) {
                true -> index = 0
                else -> index++
            }
        }
    }

    private fun findNewStartPosition(
        key: String,
        instruction: Char,
        network: Map<String, Pair<String, String>>
    ) = when (instruction == 'L') {
        true -> network.getValue(key).first
        else -> network.getValue(key).second
    }

    private fun parseInputFile(fileName: String): Pair<String, Map<String, Pair<String, String>>> {
        val input = fileReader.readStringsFromFile(fileName)
        val instructions = input.first()

        val network = input
            .drop(2)
            .map { it.replace("(", "").replace(")", "").replace(" ", "") }
            .map { it.split("=", ",") }
            .associate { it[0] to Pair(it[1], it[2]) }

        return Pair(instructions, network)

    }
}