package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.model.Race
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Day06Tests {
    @Autowired
    private lateinit var day06: Day06

    @Test
    fun `Should return expected result fo part A`() {
        assertEquals(288L, day06.solvePartA("day06"))
    }

    @Test
    fun `Should return expected result fo part B`() {
        assertEquals(71503L, day06.solvePartB("day06"))
    }

    @Test
    fun `Should map inputfile to times and distances`() {
        assertEquals(listOf(Race(7,9), Race(15,40), Race(30, 200)), day06.readRaces("day06"))
    }

    @ParameterizedTest
    @CsvSource(
        "4,7,9",
        "8,15,40",
        "9,30,200"
    )
    fun `Should calculate number of ways to beat record`(expected: Long, time: Long, distance: Long) {
        assertEquals(expected, day06.numberOfWaysToBeatRecord(Race(time = time, distance = distance)))
    }
}