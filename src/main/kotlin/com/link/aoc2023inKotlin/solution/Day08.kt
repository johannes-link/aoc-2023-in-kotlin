package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.model.CamelCardHand
import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component

@Component
class Day08(
    private val fileReader: FileReader
) {
    fun solvePartA(fileName: String = "day08"): Long {
        val (instructions, network) = this.parseInputFile(fileName = fileName)
        var steps: Long = 1L
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

    fun solvePartB(fileName: String = "day08"): Long {
        val (instructions, network) = this.parseInputFile(fileName = fileName)
        var startPositions = network.keys.filter { it.last() == 'A' }
        var steps = 1L
        var index = 0

        while (true) {
            startPositions = startPositions
                .map { findNewStartPosition(key = it, instruction = instructions[index], network = network) }

            if (startPositions.all { it.endsWith('Z') }) {
                return steps
            }

            when (index == instructions.length-1) {
                true -> index = 0
                else -> index++
            }

            if(steps == Long.MAX_VALUE){
                println("ALARM")
            }

            if (steps%10000000 == 0L){
                println(steps)
            }

            steps++
        }
    }

    private fun findNewStartPosition(
        key: String,
        instruction: Char,
        network: Map<String, Pair<String, String>>
    ) = when (instruction == 'L') {
        true -> network[key]!!.first
        else -> network[key]!!.second
    }

    private fun parseInputFile(fileName: String): Pair<String, Map<String, Pair<String, String>>> {
        var input = fileReader.readStringsFromFile(fileName)
        val instructions = input.first()

        val network = input
            .drop(2)
            .map { it.replace("(", "").replace(")", "").replace(" ", "") }
            .map { it.split("=", ",") }
            .associate { it[0] to Pair(it[1], it[2]) }

        return Pair(instructions, network)

    }
}