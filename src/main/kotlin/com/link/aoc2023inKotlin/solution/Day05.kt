package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component
import kotlin.math.min

@Component
class Day05(
    private val fileReader: FileReader
) {
    fun solvePartA(fileName: String): Long {
        val (seeds, almanac) = getSeedsAndAlmanac(fileName = fileName)

        return seeds.minOf { it.getDestination(almanac = almanac) }
    }

    fun solvePartB(fileName: String): Long {
        val (seeds, almanac) = getSeedsAndAlmanac(fileName = fileName)
        val results = mutableListOf<Long>()

        while (seeds.isNotEmpty()) {
            val source = seeds.removeFirst()
            val range = seeds.removeFirst()

            val result = (source..<source+range)
                .minOf { it.getDestination(almanac = almanac) }

            results.add(result)

            println("Proceeded $source")
            println("Result: $result")
            println()
        }

        return results.min()
    }

    private fun getSeedsAndAlmanac(fileName: String): Pair<List<Long>, List<List<List<Long>>>> {
        val almanac = fileReader
            .readStringsFromFile(fileName)
            .joinToString (";" )
            .split(";;")
            .map { it.toAlmanac() }

        val seeds = almanac.first()
        almanac.removeFirst()

        return Pair(seeds.first(), almanac)
    }

    fun String.toAlmanac() = this
        .removePrefix("seeds: ")
        .substringAfter(":;")
        .split(";")
        .map { list -> list.split(" ").map { long -> long.toLong() } }

    fun Long.getDestination(almanac: List<List<List<Long>>>): Long {
        var value = this

        almanac
            .forEach outer@{ list ->
                list.forEach {
                    val (destination, source, range) = it
                    if (value in (source..<source+range)) {
                       value = destination + (value-source)
                       return@outer
                    }
                }
            }

        return value
    }
}
