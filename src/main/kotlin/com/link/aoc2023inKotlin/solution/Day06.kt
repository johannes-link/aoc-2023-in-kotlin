package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.model.Race
import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component

@Component
class Day06(
    private val fileReader: FileReader
) {
    fun solvePartA(fileName: String = "day06") = readRaces(fileName = fileName)
            .map { numberOfWaysToBeatRecord(it) }
            .reduce { acc, result -> acc*result }

    fun solvePartB(fileName: String = "day06") = readSingleRace(fileName = fileName)
        .let { numberOfWaysToBeatRecord(it) }

    fun readRaces(fileName: String): List<Race> {
        val (times, distances) = readTimesAndDistances(fileName = fileName)

        return times.mapIndexed { index, time -> Race(time, distances[index]) }
    }

    fun numberOfWaysToBeatRecord(race: Race) = (0..race.time)
            .filterIndexed { index, millimeterPerMillisecond ->
                (race.time - index) * millimeterPerMillisecond > race.distance
            }
            .size
            .toLong()

    fun readTimesAndDistances(fileName: String) = fileReader
            .readStringsFromFile(fileName)
            .map { line -> line.substringAfter(":").split(" ").filterNot { it.isEmpty() }.map { it.toLong() } }

    private fun readSingleRace(fileName: String): Race {
        val (times, distances) = readTimesAndDistances(fileName = fileName)
        return Race(
            time = times.joinToString("").toLong(),
            distance = distances.joinToString("").toLong()
        )
    }
}
